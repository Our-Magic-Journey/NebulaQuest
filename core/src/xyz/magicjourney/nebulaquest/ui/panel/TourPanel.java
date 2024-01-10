package xyz.magicjourney.nebulaquest.ui.panel;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Queue;

import xyz.magicjourney.nebulaquest.animation.AnimatedImage;
import xyz.magicjourney.nebulaquest.event.ParameterizedEvent;
import xyz.magicjourney.nebulaquest.event.ParameterizedEventGetter;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.button.ActionButton;
import xyz.magicjourney.nebulaquest.ui.dialog.MessageBox;

public class TourPanel extends Panel {
  protected Queue<Player> players;
  protected ActionButton roll;
  protected ActionButton endTurn;
  protected Cell<?> buttonCell;
  protected Label money;
  protected AnimatedImage playerImage;
  protected MessageBox playerTurnMsg;
  protected AssetManager assets;
  protected Cell<?> playerCell;

  protected ParameterizedEvent<Player> turnStartedEvent;

  public TourPanel(AssetManager assets, ArrayList<Player> players) {
    super(207, 115, assets);

    this.assets = assets;
    this.players = new Queue<>();
    players.forEach(this.players::addLast);

    this.playerTurnMsg = new MessageBox("", assets);
    this.roll = new ActionButton("Roll", assets);
    this.endTurn = new ActionButton("End turn", assets);
    this.money = new Label(this.players.first().getMoney() + "$", this.skin);
    this.playerImage = this.players.first().getShip(assets);

    this.turnStartedEvent = new ParameterizedEvent<>();

    Table playerDescription = new Table();
    this.playerCell = playerDescription.add(playerImage);
    this.playerCell.expand().center();
    playerDescription.add(money).expand().center();
    
    this.players.first().onChange().subscribe(this.update);

    this.content.add(playerDescription).expand().fill();
    this.content.row();
    this.buttonCell = this.content.add(roll).fillX().height(20);
    
    this.endTurn.onClick().subscribe(this.handleTurnEnd);
  }

  @Override
  public void act(float delta) {
    super.act(delta);
  }

  public ParameterizedEventGetter<Runnable> onRoll() {
    return this.roll.onClick();
  }

  public ParameterizedEventGetter<Player> onTurnStarted() {
    return this.turnStartedEvent;
  }

  public void setRollMode() {
    this.buttonCell.clearActor();
    this.buttonCell.setActor(roll);
  }

  public void setTurnEndMode() {
    this.endTurn.setDisabled(true);
    this.buttonCell.clearActor();
    this.buttonCell.setActor(endTurn);
  }

  protected Consumer<Player> update = (Player player) -> {
    this.money.setText(this.players.first().getMoney() + "$");
  };

  public void blockTurnButton() {
    this.endTurn.setDisabled(true);
  }

  public void unblockTurnButton() {
    this.endTurn.setDisabled(false);
  }

  protected Consumer<Runnable> handleTurnEnd = (unblock) -> {
    this.players.first().onChange().unsubscribe(this.update);
    this.players.addLast(this.players.removeFirst());
    this.players.first().onChange().subscribe(this.update);

    this.playerImage = this.players.first().getShip(assets);
    this.playerCell.clearActor();
    this.playerCell.setActor(this.playerImage);
    this.playerCell.getTable().pack();
    this.playerTurnMsg.setText("It is the turn of " + this.players.first().getName() + "!");
    this.playerTurnMsg.show(this.getStage());

    this.update.accept(this.players.first());

    this.playerTurnMsg.onAccepted().subscribe(() -> {
      this.setRollMode();
      this.turnStartedEvent.emit(this.players.first());
    });
  };
}
