package xyz.magicjourney.nebulaquest.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xyz.magicjourney.nebulaquest.assets.AssetsLoader;
import xyz.magicjourney.nebulaquest.music.MusicManager;

@FunctionalInterface
public interface ScreenConstructor {
  AbstractScreen create(SpriteBatch batch, AssetsLoader assets, ScreenManager screenManager, MusicManager musicManager);
}