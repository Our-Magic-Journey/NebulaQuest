package xyz.magicjourney.nebulaquest.board.field;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import xyz.magicjourney.nebulaquest.animation.AnimatedImage;
import xyz.magicjourney.nebulaquest.entity.entities.planet.Planet;

public class PlanetField extends Field {
  Planet entity;
  AnimatedImage planet;
  Image color;
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
    
    this.planet = new AnimatedImage("animations/planet/" + name + ".atlas", "rotation", 0.2f, true, assets);
    this.planet.setPosition(1, 9);
  }

  @Override
  public void setColor(Color color) {
    this.color.setColor(color);
  }

  @Override
  public PlanetField clone(AssetManager assets) {
    PlanetField field = new PlanetField(entity, assets);
    
    return field;
  }
}
