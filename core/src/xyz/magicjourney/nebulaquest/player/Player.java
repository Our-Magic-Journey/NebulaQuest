package xyz.magicjourney.nebulaquest.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Player {
  private static int nextID = 0;
  
  protected int id;
  protected String name;

  public Player(String name) {
    this.name = name;
    this.id = generateId();
  }

  private int generateId() {
    return Player.nextID++;
  }

  public String getName() {
    return this.name;
  }

  public TextureRegionDrawable getShip(AssetManager assets) {
    return new TextureRegionDrawable(new TextureRegion(assets.get("images/player" + id + ".png", Texture.class)));
  }
}
