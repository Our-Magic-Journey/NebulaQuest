package xyz.magicjourney.nebulaquest.board.field;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.entity.Entity;

public class BigField extends Field {
  public BigField(Entity entity, AssetManager assets) {
    this(entity, "images/big-field", assets);
  }

  public BigField(Entity entity, String texture, AssetManager assets) {
    super(entity, texture, assets, 64, 64);
  }
}
