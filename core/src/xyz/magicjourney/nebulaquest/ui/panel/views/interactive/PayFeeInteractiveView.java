package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import java.util.function.Consumer;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.entity.Buyable;
import xyz.magicjourney.nebulaquest.entity.Describable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.button.ActionButton;
import xyz.magicjourney.nebulaquest.ui.panel.InteractivePanel;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;


public class PayFeeInteractiveView extends DescriptionInteractiveView {
  protected ActionButton payButton;
  protected ActionButton bankruptButton;

  protected Buyable property;
  protected Player player;

  public PayFeeInteractiveView(AssetManager assets, InteractivePanel parent, TourPanel tourPanel) {
    super(assets, parent, tourPanel);

    this.payButton = new ActionButton("Pay fee", true, assets);
    this.bankruptButton = new ActionButton("Bankrupt", true, assets);
    this.payButton.onClick().subscribe(this.handlePayClick);
    this.bankruptButton.onClick().subscribe(this.handleBankruptClick);
  }

  protected void generateDescription() {
    String text = this.splitText(23, "This planet is owned by " + this.property.getOwner().get().getName() + "! You must pay it " + this.property.getFee() + " credits as a residence fee.");

    this.description.setText(text);
  }

  @Override
  public void createLayout(Describable entity, boolean displayDescription) {
    super.createLayout(entity, true);

    this.addSpacer();
    this.add(this.payButton).height(20).pad(2, 4, 0, 4).fillX().row();
    this.add(this.bankruptButton).height(20).pad(4, 4, 0, 4).fillX().row();
  }

  @Override
  public void prepareForNextTurn() {
    super.prepareForNextTurn();

    if (this.player != null) {
      this.player.onChange().unsubscribe(this.update);
    }

    this.player = null;
    this.payButton.setDisabled(true);
    this.bankruptButton.setDisabled(true);
  }

  @Override
  public void select(Player player, Entity field) {
    this.display(field);

    if (this.player != null) {
      this.parent.select("Description");
      return;
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

  protected void showDescriptionInstead() {
    this.parent.select("Description");
    this.tourPanel.unblockTurnButton();
  }

  protected Consumer<Player> update = (player) -> {
    this.payButton.setDisabled(true);

    if (!property.mustPayFee(player)) {
      return;
    }

    if (player.getMoney() < this.property.getFee()) {
      return;
    }

    this.payButton.setDisabled(false);
  };

  protected Consumer<Runnable> handlePayClick = (unblock) -> {
    this.bankruptButton.setDisabled(true);
    this.player.payFee(this.property);

    this.showDescriptionInstead();
  };

  protected Consumer<Runnable> handleBankruptClick = (unblock) -> {
    this.payButton.setDisabled(true);
    this.property.getOwner().get().giveMoney(property.getFee());
    this.player.bankrupt();
  };
}
