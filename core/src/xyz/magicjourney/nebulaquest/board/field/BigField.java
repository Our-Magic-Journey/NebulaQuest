package xyz.magicjourney.nebulaquest.board.field;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.entity.Entity;

public class BigField extends Field {
  public BigField(Entity entity, AssetManager assets) {
    super(entity, assets, 64, 64);

    this.setTexture("images/big-field", assets);
  }

  @Override
  public BigField clone(AssetManager assets) {
    BigField field = new BigField(entity, assets);
    
    return field;
  }
}
