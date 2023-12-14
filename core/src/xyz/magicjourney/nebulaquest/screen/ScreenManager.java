package xyz.magicjourney.nebulaquest.screen;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import xyz.magicjourney.nebulaquest.NebulaQuest;
import xyz.magicjourney.nebulaquest.music.MusicManager;

public class ScreenManager {
  public HashMap<String, AbstractScreen> screens;
  protected SpriteBatch batch;
  protected AssetManager assets;
  protected NebulaQuest game;
  protected MusicManager musicManager;

  public ScreenManager(NebulaQuest game, SpriteBatch batch, AssetManager assets, MusicManager musicManager) {
    this.game = game;
    this.batch = batch;
    this.assets = assets;
    this.screens = new HashMap<>();
    this.musicManager = musicManager;
  }

  public void register(String name, ScreenConstructor constructor) {
    this.screens.put(name, constructor.create(batch, assets, this, musicManager));
  }

  public void select(String name) {
    if (screens.containsKey(name)) {
      game.setScreen(screens.get(name));
    }
  }
}
