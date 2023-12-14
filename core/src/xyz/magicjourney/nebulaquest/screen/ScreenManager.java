package xyz.magicjourney.nebulaquest.screen;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import xyz.magicjourney.nebulaquest.NebulaQuest;

public class ScreenManager {
  public HashMap<String, AbstractScreen> screens;
  protected SpriteBatch batch;
  protected AssetManager assets;
  protected NebulaQuest game;

  public ScreenManager(NebulaQuest game, SpriteBatch batch, AssetManager assets) {
    this.game = game;
    this.batch = batch;
    this.assets = assets;
    this.screens = new HashMap<>();
  }

  public void register(String name, ScreenConstructor constructor) {
    this.screens.put(name, constructor.create(batch, assets, this));
  }

  public void select(String name) {
    if (screens.containsKey(name)) {
      game.setScreen(screens.get(name));
    }
  }
}
