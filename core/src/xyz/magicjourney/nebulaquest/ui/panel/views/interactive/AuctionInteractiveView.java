package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import java.util.function.Consumer;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.entity.Buyable;
import xyz.magicjourney.nebulaquest.entity.Describable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.button.ActionButton;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;
import xyz.magicjourney.nebulaquest.ui.panel.ViewPanel;

public class AuctionInteractiveView extends DescriptionInteractiveView {
  protected ActionButton buyButton;
  protected ActionButton skipButton;

  protected Buyable property;
  protected Player player;

  public AuctionInteractiveView(AssetManager assets, ViewPanel<?> parent, TourPanel tourPanel) {
    super(assets, parent, tourPanel);

    this.buyButton = new ActionButton("Buy", true, assets);
    this.skipButton = new ActionButton("Skip", true, assets);
    this.buyButton.onClick().subscribe(this.handleBuyClick);
    this.skipButton.onClick().subscribe(this.handleSkipClick);
  }

  @Override
  public void createLayout(Describable entity, boolean displayDescription) {
    super.createLayout(entity, true);

    this.addSpacer();
    this.add(this.buyButton).height(20).pad(2, 4, 0, 4).fillX().row();;
    this.add(this.skipButton).height(20).pad(4, 4, 0, 4).fillX().row();
  }

  protected void generateDescription() {
    String text = this.splitText(23, "The planet is currently under the control of the Republic, engaged in a war against separatists. The Republic, in need of resources, is willing to sell this world. As the closest explorer, you have the right of first refusal.");

    this.description.setText(text);
  }

  @Override
  public void select(Player player, Entity field) {
    this.display(field);

    if (this.player != null) {
      this.player.onChange().unsubscribe(this.update);
    }
    
    this.player = player;
    this.player.onChange().subscribe(this.update);

    if (field instanceof Buyable property) {
      this.property = property;
      this.skipButton.setDisabled(false);

      if (property.canByBought() && player.getMoney() >= property.getValue()) {
        this.buyButton.setDisabled(false);
        this.generateDescription();

        return;
      }
    }

    this.showDescriptionInstead();
  }

  public void showDescriptionInstead() {
    this.parent.select("Description");
    this.tourPanel.unblockTurnButton();
  }

  protected Consumer<Player> update = (player) -> {
    this.buyButton.setDisabled(true);

    if (!this.property.canByBought()) {
      return;
    }

    if (player.getMoney() < this.property.getValue()) {
      return;
    }

    this.buyButton.setDisabled(false);
  };

  protected Consumer<Runnable> handleBuyClick = (unblock) -> {
    this.skipButton.setDisabled(true);
    this.player.buyProperty(this.property);
    this.showDescriptionInstead();
  };

  protected Consumer<Runnable> handleSkipClick = (unblock) -> {
    this.buyButton.setDisabled(true);
    this.showDescriptionInstead();
  };
}
