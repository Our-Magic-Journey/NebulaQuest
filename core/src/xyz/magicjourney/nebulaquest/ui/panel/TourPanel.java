package xyz.magicjourney.nebulaquest.ui.panel;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Queue;

import xyz.magicjourney.nebulaquest.event.Event;
import xyz.magicjourney.nebulaquest.event.EventGetter;
import xyz.magicjourney.nebulaquest.event.ParameterizedEvent;
import xyz.magicjourney.nebulaquest.event.ParameterizedEventGetter;
import xyz.magicjourney.nebulaquest.listener.Listener;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.dialog.MessageBox;

public class TourPanel extends Panel {
  protected Queue<Player> players;
  protected TextButton roll;
  protected TextButton endTurn;
  protected Cell<?> buttonCell;
  protected Label money;
  protected Image playerImage;
  protected MessageBox playerTurnMsg;
  protected AssetManager assets;

  protected Event rollEvent;
  protected ParameterizedEvent<Player> turnStartedEvent;

  public TourPanel(AssetManager assets, ArrayList<Player> players) {
    super(207, 115, assets);

    this.assets = assets;
    this.players = new Queue<>();
    players.forEach(this.players::addLast);

    this.playerTurnMsg = new MessageBox("", assets);
    this.roll = new TextButton("Roll", this.skin, "orange");
    this.endTurn = new TextButton("End turn", this.skin);
    this.money = new Label("1000$", this.skin);
    this.playerImage = new Image(this.players.first().getShip(assets));

    this.rollEvent = new Event();
    this.turnStartedEvent = new ParameterizedEvent<>();

    Table playerDescription = new Table();
    playerDescription.add(playerImage).expand().center();
    playerDescription.add(money).expand().center();
    

    this.content.add(playerDescription).expand().fill();
    this.content.row();
    this.buttonCell = this.content.add(roll).fillX();
    
    this.roll.addListener(new Listener(rollEvent::emit));
    this.endTurn.addListener(new Listener(this::handleTurnEnd));
    this.playerTurnMsg.onAccepted().subscribe(() -> turnStartedEvent.emit(this.players.first()));
  }

  @Override
  public void act(float delta) {
    super.act(delta);
  }

  public EventGetter onRoll() {
    return this.rollEvent;
  }

  public ParameterizedEventGetter<Player> onTurnStarted() {
    return this.turnStartedEvent;
  }

  public void setRollMode() {
    this.buttonCell.clearActor();
    this.buttonCell.setActor(roll);
  }

  public void setTurnEndMode() {
    this.buttonCell.clearActor();
    this.buttonCell.setActor(endTurn);
  }

  protected void handleTurnEnd() {
    this.players.addLast(this.players.removeFirst());
    this.playerImage.setDrawable(this.players.first().getShip(assets));
    this.playerTurnMsg.setText("It is the turn of " + this.players.first().getName() + "!");
    this.playerTurnMsg.show(this.getStage());
    this.setRollMode();
  }
}
