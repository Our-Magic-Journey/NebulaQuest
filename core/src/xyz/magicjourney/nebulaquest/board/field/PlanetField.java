package xyz.magicjourney.nebulaquest.board.field;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import xyz.magicjourney.nebulaquest.entity.entities.planet.Planet;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class PlanetField extends Field {
  Planet entity;
  Array<AtlasRegion> textures;
  Animation<AtlasRegion> animation;
  TextureRegionDrawable planetTexture;
  Image planet;
  Image color;
  float time;
  Label price;
  Label name;
  
  public PlanetField(Planet entity, AssetManager assets) {
    super(entity, assets);

    this.entity = entity;
    this.color = new Image(assets.get("images/planet-field-color.png", Texture.class));
    this.name = new Label("Planet", assets.get("skin/ui.skin.json", Skin.class), "mini-micro");
    this.price = new Label("100$", assets.get("skin/ui.skin.json", Skin.class), "micro");

    this.setTexture("images/planet-field", assets);
    this.loadData(assets);
    
    this.entity.onChange().subscribe(() -> this.loadData(assets));

    this.top();
    this.add().height(2).row();
    this.add(this.color).height(5).row();
    this.add().height(2).row();
    this.add(planet).row();
    this.add().height(2).row();
    this.add(name).height(7).row();
    this.add().height(3).row();
    this.add(price);
    this.add().height(7).row();
  }

  protected void loadData(AssetManager assets) {
    this.loadAnimation(assets);
    this.color.setColor(this.entity.getRegion().getColor());
    this.name.setText(this.entity.getName());
    this.price.setText(this.entity.getValue());
  }

  protected void loadAnimation(AssetManager assets) {
    String name = this.entity.getName();

    this.textures = assets.get("animations/planet/" + name + ".atlas", TextureAtlas.class).findRegions("rotation");
    this.animation = new Animation<>(0.2f, this.textures, PlayMode.LOOP);
    this.planetTexture = new TextureRegionDrawable(this.animation.getKeyFrame(0));
    this.planet = new Image(this.planetTexture);
    this.planet.setPosition(1, 9);
  }

  @Override
  public void setColor(Color color) {
    this.color.setColor(color);
  }

  @Override
  public void act(float delta) {
    super.act(delta);

    this.time += delta;
    this.planetTexture.setRegion(animation.getKeyFrame(time)); 
  }

  @Override
  public PlanetField clone(AssetManager assets) {
    PlanetField field = new PlanetField(entity, assets);
    
    return field;
  }
}
