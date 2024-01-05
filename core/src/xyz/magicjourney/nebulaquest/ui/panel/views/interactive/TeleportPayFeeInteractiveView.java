package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.entity.Buyable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;
import xyz.magicjourney.nebulaquest.ui.panel.ViewPanel;

public class TeleportPayFeeInteractiveView extends PayFeeInteractiveView {
  public TeleportPayFeeInteractiveView(AssetManager assets, ViewPanel<?> parent, TourPanel tourPanel) {
    super(assets, parent, tourPanel);
  }

  @Override
  protected void generateDescription() {
    String text = this.splitText(23, "Emerging from hyperspace, you find yourself in a system filled with heavily armed ships, all belonging to " + this.property.getOwner().get().getName() + ". What a cunning move! However, if you wish to survive, you must pay the entrance fee for access to this 'private' territory.");

    this.description.setText(text);
  }

  @Override
  public void select(Player player, Entity field) {
    this.display(field);

    if (this.player != null && this.property == field) {
      this.parent.select("Teleport");
      return;
    }

    if (this.property != field) {
      this.tourPanel.blockTurnButton();
    }

    this.player = player;
    this.player.onChange().subscribe(this.update);

    if (field instanceof Buyable property) {
      this.property = property;
      this.bankruptButton.setDisabled(false);

      if (property.mustPayFee(player) && player.getMoney() >= property.getFee()) {
        this.payButton.setDisabled(false);
        this.generateDescription();

        return;
      }
    }

    this.showDescriptionInstead();
  }

  @Override
  protected void showDescriptionInstead() {
    this.parent.select("Teleport");
    this.tourPanel.unblockTurnButton();
  }
}
