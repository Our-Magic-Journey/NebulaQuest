package xyz.magicjourney.nebulaquest.player;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import xyz.magicjourney.nebulaquest.entity.Buyable;
import xyz.magicjourney.nebulaquest.entity.Describable;
import xyz.magicjourney.nebulaquest.event.ParameterizedEvent;
import xyz.magicjourney.nebulaquest.event.ParameterizedEventGetter;

public class Player implements Describable {
  private static int nextID = 0;
  
  protected ParameterizedEvent<Player> changedEvent;
  
  protected int id;
  protected String name;
  protected int money;
  protected ArrayList<Buyable> properties;

  public Player(String name) {
    this.name = name;
    this.id = generateId();
    this.money = 1000;
    this.changedEvent = new ParameterizedEvent<>();
    this.properties = new ArrayList<>();
  }

  private int generateId() {
    return Player.nextID++;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return "An ordinary trader, like many others in the Galaxy";
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

  @Override
  public Actor getIcon(AssetManager assets) {
    return new Image(this.getShip(assets));
  }

  public ParameterizedEventGetter<Player> onChange() {
    return this.changedEvent;
  }
}
