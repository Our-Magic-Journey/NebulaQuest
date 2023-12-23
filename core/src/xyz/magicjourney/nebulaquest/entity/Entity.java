package xyz.magicjourney.nebulaquest.entity;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.event.Event;
import xyz.magicjourney.nebulaquest.event.EventGetter;
import xyz.magicjourney.nebulaquest.player.Player;

public abstract class Entity {
  protected Event changeEvent;

  public Entity() {
    this.changeEvent = new Event();
  }

  public abstract void onEnter(Player player);
  public abstract void onPass(Player player);
  public abstract Field toField(AssetManager assets);

  public EventGetter onChange() {
    return changeEvent;
  }
}
