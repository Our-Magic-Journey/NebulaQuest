package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import java.util.Random;
import java.util.function.Consumer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import xyz.magicjourney.nebulaquest.entity.Describable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.button.ActionButton;
import xyz.magicjourney.nebulaquest.ui.dialog.MessageBox;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;
import xyz.magicjourney.nebulaquest.ui.panel.ViewPanel;

public class CasinoInteractiveView extends DescriptionInteractiveView {
  protected Random random;
  protected ActionButton oddButton;
  protected ActionButton evenButton;
  protected ActionButton bankruptButton;
  protected ActionButton decrease;
  protected ActionButton increase;
  protected MessageBox bankruptConfirmation;
  protected Label bet;
  protected int betValue;
  protected Player player;

  public CasinoInteractiveView(AssetManager assets, ViewPanel<?> parent, TourPanel tourPanel) {
    super(assets, parent, tourPanel);
    
    this.random = new Random();
    this.increase = new ActionButton("Add 100", true, assets);
    this.decrease = new ActionButton("Remove 100", true, assets);
    this.oddButton = new ActionButton("Odds", true, assets);
    this.evenButton = new ActionButton("Even", true, assets);
    this.bankruptButton = new ActionButton("Bankrupt", false, assets);
    this.bankruptConfirmation = new MessageBox("Unimplemented :(", assets);

    this.betValue = 0;
    this.bet = new Label("Your bet: " + this.betValue, this.skin, "small");

    this.oddButton.onClick().subscribe(this.handleOddButtonClick);
    this.evenButton.onClick().subscribe(this.handleEvenButtonClick);
    this.bankruptButton.onClick().subscribe(this.handleBankruptClick);
    this.decrease.onClick().subscribe(this.handleDecreaseButtonClick);
    this.increase.onClick().subscribe(this.handleIncreaseButtonClick);
  }

  @Override
  public void select(Player player, Entity field) {
    super.select(player, field);

    if (this.player != null) {
      this.player.onChange().unsubscribe(handlePlayerUpdate);
    }

    player.onChange().subscribe(handlePlayerUpdate);

    if (this.player != player) {
      this.player = player;
      this.resetCasino();  
    }
    this.description.setText(this.splitText(23, "You docked to a suspicious-looking space station, only to discover it's a cosmic casino run by local gangsters. The catch is, you can only leave the station if you engage in a game with them. Sorry it's company policy. So what's your bet?"));
  }

  protected void resetCasino() {
    this.betValue = 0;
    this.bet.setText("Your bet: " + this.betValue);
    this.increase.setDisabled(this.player.getMoney() < 100);
    this.decrease.setDisabled(true);
    this.oddButton.setDisabled(true);
    this.evenButton.setDisabled(true);
    this.bankruptButton.setDisabled(false);
  }

  @Override
  protected void createLayout(Describable entity, boolean displayDescription) {
    super.createLayout(entity, displayDescription);

    this.addSpacer(0, 0, 4, 0);
    this.add(this.increase).height(20).pad(0, 4, 4, 4).fillX().row();
    this.add(this.decrease).height(20).pad(0, 4, 4, 4).fillX().row();
    this.addSpacer();
    this.add(this.bet).expandX().top().row();
    this.addSpacer(0, 0, 1, 0);
    this.add(this.evenButton).height(20).pad(1, 4, 0, 4).fillX().row();
    this.add(this.oddButton).height(20).pad(1, 4, 0, 4).fillX().row();
    this.add(this.bankruptButton).height(20).pad(1, 4, 0, 4).fillX().row();
  }

  protected Consumer<Runnable> handleOddButtonClick = (unblock) -> {
    this.evenButton.setDisabled(true);
    this.rollCasinoDice(this.player, false);
  };

  protected Consumer<Runnable> handleEvenButtonClick = (unblock) -> {
    this.oddButton.setDisabled(true);
    this.rollCasinoDice(this.player, true);
  };

  protected void rollCasinoDice(Player player, boolean even) {
    parent.getDice().roll((result) -> {
      boolean rolledEven = result % 2 == 0;
      
      if(even == rolledEven) {
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
    });
  }
  
  protected Consumer<Runnable> handleIncreaseButtonClick = (unblock) -> {
    if(this.player.pay(100)) {
      this.betValue += 100;
      this.bet.setText("Your bet: " + this.betValue);

      this.decrease.setDisabled(false);
      this.oddButton.setDisabled(false);
      this.evenButton.setDisabled(false);
      this.bankruptButton.setDisabled(true);
      
      if (this.player.getMoney() >= 100) {
        unblock.run();
      }
    }
  };

  protected Consumer<Runnable> handleDecreaseButtonClick = (unblock) -> {
    this.player.giveMoney(100);
    this.betValue -= 100;
    this.bet.setText("Your bet: " + this.betValue);
    
    if(this.betValue >= 100) {
      unblock.run();
    }
    else {
      this.oddButton.setDisabled(true);
      this.evenButton.setDisabled(true);
      this.bankruptButton.setDisabled(false);
    }
  };

  protected Consumer<Runnable> handleBankruptClick = (unblock) -> {
    this.bankruptConfirmation.show(this.getStage());

    this.increase.setDisabled(true);
    this.decrease.setDisabled(true);
    this.oddButton.setDisabled(true);
    this.evenButton.setDisabled(true);

    this.tourPanel.unblockTurnButton();
  };

  protected Consumer<Player> handlePlayerUpdate = (player) -> {
    increase.setDisabled(player.getMoney() < 100);
  };
}
