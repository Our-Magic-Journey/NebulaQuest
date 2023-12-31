package xyz.magicjourney.nebulaquest.entity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Actor;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.event.Event;
import xyz.magicjourney.nebulaquest.event.EventGetter;
import xyz.magicjourney.nebulaquest.player.Player;

public abstract class Entity implements Describable {
  protected Event changeEvent;
  protected String name;
  protected String description;

  public Entity(String name, String description) {
    this.changeEvent = new Event();
    this.name = name;
    this.description = description;
  }

  public abstract void onEnter(Player player);
  public abstract void onPass(Player player);
  public abstract Field toField(AssetManager assets);

  @Override
  public Actor getIcon(AssetManager assets) {
    return this.toField(assets);
  }

  public EventGetter onChange() {
    return changeEvent;
  }

  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
}
