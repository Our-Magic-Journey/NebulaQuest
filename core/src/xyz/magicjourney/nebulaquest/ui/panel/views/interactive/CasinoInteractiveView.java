package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import java.util.Random;
import java.util.function.Consumer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import xyz.magicjourney.nebulaquest.entity.Describable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.listener.Listener;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.button.ActionButton;
import xyz.magicjourney.nebulaquest.ui.dialog.MessageBox;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;
import xyz.magicjourney.nebulaquest.ui.panel.ViewPanel;

public class CasinoInteractiveView extends DescriptionInteractiveView {
  protected ActionButton oddButton;
  protected ActionButton evenButton;
  protected ActionButton bankruptButton;
  protected MessageBox bankruptConfirmation;
  protected Label bet;
  protected TextButton increase;
  protected TextButton decrease;
  protected int betValue;
  protected Player player;
  protected Random random;

  public CasinoInteractiveView(AssetManager assets, ViewPanel<?> parent, TourPanel tourPanel) {
    super(assets, parent, tourPanel);
    this.increase = new TextButton("Add 100", this.skin, "orange");
    this.decrease = new TextButton("Remove 100", this.skin, "orange");
    this.decrease.setDisabled(true);
    this.oddButton = new ActionButton("Odds", true, assets);
    this.evenButton = new ActionButton("Even", true, assets);
    this.bankruptButton = new ActionButton("Bankrupt", false, assets);
    this.bankruptConfirmation = new MessageBox("Unimplemented :(", assets);
    this.betValue = 0;
    this.bet = new Label("Your bet: " + this.betValue, this.skin, "small");
    this.oddButton.onClick().subscribe(this.handleOddButtonClick);
    this.evenButton.onClick().subscribe(this.handleEvenButtonClick);
    this.bankruptButton.onClick().subscribe(this.handleBankruptClick);
    this.increase.addListener(new Listener(() -> {

      if(this.player.pay(100)) {
        this.betValue += 100;
        this.bet.setText("Your bet: " + this.betValue);
        this.decrease.setDisabled(false);
        this.oddButton.setDisabled(false);
        this.evenButton.setDisabled(false);
        this.bankruptButton.setDisabled(true);
      }

    }));
    this.decrease.addListener(new Listener(() -> {
      if(this.betValue <= 100) {
        this.decrease.setDisabled(true);
      }
      else {
        this.player.giveMoney(100);
        this.betValue -= 100;
        this.bet.setText("Your bet: " + this.betValue);
      }
    }));

    this.random = new Random();
  }

  @Override
  public void select(Player player, Entity field) {
    super.select(player, field);

    if (this.player != player) {
      this.player = player;
      this.prepereCasino();
    }
    this.description.setText(this.splitText(23, "Hello " + player.getName() + " Welcome in to the Casino"));

  }

  @Override
  protected void createLayout(Describable entity, boolean displayDescription) {
    super.createLayout(entity, displayDescription);
    this.addSpacer();
    this.add(this.increase).height(20).pad(2, 4, 0, 4).fillX().row();
    this.add(this.decrease).height(20).pad(4, 4, 0, 4).fillX().row();
    this.addSpacer();
    this.add(this.bet).expandX().top().row();
    this.addSpacer();
    this.add(this.evenButton).height(20).pad(2, 4, 0, 4).fillX().row();
    this.add(this.oddButton).height(20).pad(4, 4, 0, 4).fillX().row();
    this.add(this.bankruptButton).height(20).pad(4, 4, 0, 4).fillX().row();
  }

  private void rollCasinoDice(Player player, boolean i) {
    boolean randBool = this.random.nextBoolean();

    if(i == randBool) {
      this.player.giveMoney((int) (betValue * 1.5));
      this.bet.setText("You won: " + ((int) (betValue * 1.5)));
    }
    else {
      this.bet.setText("You lost: " + this.betValue);
    }

    this.increase.setDisabled(true);
    this.decrease.setDisabled(true);
    this.oddButton.setDisabled(true);
    this.evenButton.setDisabled(true);

    this.tourPanel.unblockTurnButton();
  }

  private void prepereCasino() {
    this.betValue = 0;
    this.bet.setText("Your bet: " + this.betValue);
    this.increase.setDisabled(this.player.getMoney() < 100);
    this.decrease.setDisabled(true);
    this.oddButton.setDisabled(true);
    this.evenButton.setDisabled(true);
    this.bankruptButton.setDisabled(false);

  }

  protected Consumer<Runnable> handleOddButtonClick = (unblock) -> {
    this.evenButton.setDisabled(true);
    rollCasinoDice(this.player, true);
  };

  protected Consumer<Runnable> handleEvenButtonClick = (unblock) -> {
    this.oddButton.setDisabled(true);
    rollCasinoDice(this.player, false);
  };

  protected Consumer<Runnable> handleBankruptClick = (unblock) -> {
    this.bankruptConfirmation.show(this.getStage());
    this.increase.setDisabled(true);
    this.decrease.setDisabled(true);
    this.oddButton.setDisabled(true);
    this.evenButton.setDisabled(true);
    this.tourPanel.unblockTurnButton();

  };
}
