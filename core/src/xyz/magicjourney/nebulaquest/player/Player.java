package xyz.magicjourney.nebulaquest.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Player {
  private static int nextID = 0;
  
  protected int id;
  protected String name;
  protected int money;

  public Player(String name) {
    this.name = name;
    this.id = generateId();
    this.money = 1000;
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

  public TextureRegionDrawable getShip(AssetManager assets) {
    return new TextureRegionDrawable(new TextureRegion(assets.get("images/player" + id + ".png", Texture.class)));
  }
}
