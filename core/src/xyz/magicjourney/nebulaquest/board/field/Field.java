package xyz.magicjourney.nebulaquest.board.field;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import xyz.magicjourney.nebulaquest.board.Pawn;
import xyz.magicjourney.nebulaquest.entity.Entity;

public class Field extends Button {
  protected ButtonStyle style;
  protected Entity entity;
  protected int correctPawnRotation;

  public Field(Entity entity, AssetManager assets) {
    this(entity, "images/small-field", assets);
  }

  public Field(Entity entity, String texture, AssetManager assets) {
    this(entity, texture, assets, 32, 64);
  }

  public Field(Entity entity, String texture, AssetManager assets, int width, int height) {
    this.style = new ButtonStyle();
    this.entity = entity;
    this.correctPawnRotation = 0;

    this.setWidth(width);
    this.setHeight(height);
    this.setStyle(this.style);
    this.setTexture(texture, assets);
    
    // Rotate children with parent
    this.setTransform(true);
  }

  protected void setTexture(String texture, AssetManager assets) {
    this.style.up = new TextureRegionDrawable(assets.get(texture + ".png", Texture.class));
    this.style.over = new TextureRegionDrawable(assets.get(texture + "-over.png", Texture.class));
    this.style.down = new TextureRegionDrawable(assets.get(texture + "-down.png", Texture.class));

    this.style.checked = new TextureRegionDrawable(assets.get(texture + "-down.png", Texture.class));
  }

  public void correctPawnRotationBy(int correctPawnRotation) {
    this.correctPawnRotation = correctPawnRotation;
  }

  public Entity getEntity() {
    return this.entity;
  }

  public void makeStatic() {
    this.style.over = null;
    this.style.down = null;
    this.style.checked = null;
  }

  public void addPawn(Pawn pawn) {
    this.addActor(pawn);
    pawn.setPosition((this.getWidth() - pawn.getWidth())/2, (this.getHeight() - pawn.getHeight())/2);
    pawn.setRotation(correctPawnRotation);
  }
}
