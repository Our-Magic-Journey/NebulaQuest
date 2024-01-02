package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

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
    this.buyButton.onClick().subscribe(this::handleBuyClick);
    this.skipButton.onClick().subscribe(this::handleSkipClick);
  }

  @Override
  public void createLayout(Describable entity, boolean displayDescription) {
    super.createLayout(entity, false);

    this.addSpacer();
    this.add(this.buyButton).height(20).pad(2, 4, 0, 4).fillX().row();;
    this.add(this.skipButton).height(20).pad(4, 4, 0, 4).fillX().row();
  }

  @Override
  public void select(Player player, Entity field) {
    this.display(field);

    if (field instanceof Buyable property) {
      this.property = property;
      this.player = player;

      this.skipButton.setDisabled(false);

      if (!property.canByBought()) {
        return;
      }

      if (player.getMoney() < property.getValue()) {
        return;
      }

      this.buyButton.setDisabled(false);
    }
  }

  protected void handleBuyClick(Runnable unblock) {
    this.tourPanel.unblockTurnButton();
    this.skipButton.setDisabled(true);
  }

  protected void handleSkipClick(Runnable unblock) {
    this.tourPanel.unblockTurnButton();
    this.buyButton.setDisabled(true);
  }
}
