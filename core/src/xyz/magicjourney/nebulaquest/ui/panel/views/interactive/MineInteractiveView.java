package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import java.util.function.Consumer;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.entity.Describable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.entity.entities.Mine;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.button.ActionButton;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;
import xyz.magicjourney.nebulaquest.ui.panel.InteractivePanel;

public class MineInteractiveView extends DescriptionInteractiveView {
  protected ActionButton buyButton;

  protected Mine property;
  protected Player player;

  public MineInteractiveView(AssetManager assets, InteractivePanel parent, TourPanel tourPanel) {
    super(assets, parent, tourPanel);

    this.buyButton = new ActionButton("Buy", true, assets);
    this.buyButton.onClick().subscribe(this.handleBuyClick);
  }
  
  @Override
  public void createLayout(Describable entity, boolean displayDescription) {
    super.createLayout(entity, true);

    this.addSpacer();
    this.add(this.buyButton).height(20).pad(2, 4, 0, 4).fillX().row();;
  }

  protected void generateDescription() {
    String text = this.splitText(23, "There is a mine working for the Republic on this planet. Its manager is willing to share with you some 'excess' ore below market price. However, you must first prove to them that you are a trustworthy person.");

    this.description.setText(text);
  }

  @Override
  public void prepareForNextTurn() {
    if (this.player != null) {
      this.player.onChange().unsubscribe(this.update);
    }

    this.player = null;
    this.property = null;
    this.buyButton.setDisabled(true);
  }

  @Override
  public void select(Player player, Entity field) {
    this.display(field);

    if (this.player == null) {
      this.player = player;
      this.player.onChange().subscribe(this.update);
    }

    if (field instanceof Mine property) {
      this.property = property;

      if (player.getMoney() >= property.getValue()) {
        this.buyButton.setDisabled(false);
        this.generateDescription();

        return;
      }
    }
  }

  protected Consumer<Player> update = (player) -> {
    this.buyButton.setDisabled(player.getMoney() < this.property.getValue());
  };

  protected Consumer<Runnable> handleBuyClick = (unblock) -> {
    this.player.buyProperty(this.property);
    this.owner.setText("Owner: " + this.player.getName());
  };
}
