package xyz.magicjourney.nebulaquest.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

@FunctionalInterface
public interface ScreenConstructor {
  AbstractScreen create(SpriteBatch batch, AssetManager assets, ScreenManager screenManager);
}