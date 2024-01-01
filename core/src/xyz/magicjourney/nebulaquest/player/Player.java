package xyz.magicjourney.nebulaquest.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import xyz.magicjourney.nebulaquest.event.ParameterizedEvent;
import xyz.magicjourney.nebulaquest.event.ParameterizedEventGetter;

public class Player {
  private static int nextID = 0;
  
  protected ParameterizedEvent<Player> changedEvent;

  protected int id;
  protected String name;
  protected int money;

  public Player(String name) {
    this.name = name;
    this.id = generateId();
    this.money = 1000;
    this.changedEvent = new ParameterizedEvent<>();
  }

  private int generateId() {
    return Player.nextID++;
  }

  public String getName() {
    return this.name;
  }

  public int getMoney() {
    return this.money;
  }

  public void setMoney(int money) {
    this.money = money;
    this.changedEvent.emit(this);
  }

  public TextureRegionDrawable getShip(AssetManager assets) {
    return new TextureRegionDrawable(new TextureRegion(assets.get("images/player" + id + ".png", Texture.class)));
  }

  public ParameterizedEventGetter<Player> onChange() {
    return this.changedEvent;
  }
}
