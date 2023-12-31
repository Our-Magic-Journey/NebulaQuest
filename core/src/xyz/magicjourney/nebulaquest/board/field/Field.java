package xyz.magicjourney.nebulaquest.board.field;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import xyz.magicjourney.nebulaquest.entity.Entity;

public class Field extends Button {
  protected ButtonStyle style;
  protected Entity entity;

  public Field(Entity entity, AssetManager assets) {
    this(entity, assets, 32, 64);
  }

  public Field(Entity entity, AssetManager assets, int width, int height) {
    this.style = new ButtonStyle();
    this.entity = entity;

    this.setWidth(width);
    this.setHeight(height);
    this.setStyle(this.style);
    this.setTexture("images/small-field", assets);
    
    // Rotate children with parent
    this.setTransform(true);
  }

  protected void setTexture(String texture, AssetManager assets) {
    this.style.up = new TextureRegionDrawable(assets.get(texture + ".png", Texture.class));
    this.style.over = new TextureRegionDrawable(assets.get(texture + "-over.png", Texture.class));
    this.style.down = new TextureRegionDrawable(assets.get(texture + "-down.png", Texture.class));

    this.style.checked = new TextureRegionDrawable(assets.get(texture + "-down.png", Texture.class));
  }

  public Entity getEntity() {
    return this.entity;
  }

  public Field clone(AssetManager assets) {
    Field field = new Field(entity, assets);
    
    return field;
  }

  public void makeStatic() {
    this.style.over = null;
    this.style.down = null;
    this.style.checked = null;
  }
}
