package xyz.magicjourney.nebulaquest.ui.panel;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import xyz.magicjourney.nebulaquest.event.Event;
import xyz.magicjourney.nebulaquest.event.EventGetter;
import xyz.magicjourney.nebulaquest.listener.Listener;

public class TourPanel extends Panel {
  protected TextButton roll;
  protected Label money;
  protected Image player;

  protected Event rollEvent;

  public TourPanel(AssetManager assets) {
    super(207, 115, assets);

    this.roll = new TextButton("Roll", this.skin, "orange");
    this.money = new Label("1000$", this.skin);
    this.player = new Image(assets.get("images/player.png", Texture.class));
    this.rollEvent = new Event();

    Table playerDescription = new Table();
    playerDescription.add(player).expand().center();
    playerDescription.add(money).expand().center();

    this.content.add(playerDescription).expand().fill();
    this.content.row();
    this.content.add(roll).fillX();

    this.roll.addListener(new Listener(rollEvent::emit));
  }

  @Override
  public void act(float delta) {
    super.act(delta);
  }

  public EventGetter onRoll() {
    return this.rollEvent;
  }
}
