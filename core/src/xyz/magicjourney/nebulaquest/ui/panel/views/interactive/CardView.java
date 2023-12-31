package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.entity.entities.planet.Planet;
import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractView;

public class CardView extends AbstractView {
  protected Planet planet;
  protected Entity entity;
  protected Label label;

  public CardView(AssetManager assets) {
    super(assets);

    this.label = new Label("Card View", skin, "small");
    this.content.add(label);
  }

  public void setEntity(Entity entity) {
    this.entity = entity;
    this.label.setText("Card View " + this.entity.getName());

    if (entity instanceof Planet planet) {
      this.planet = planet;
    }
  }

  @Override
  public void show() {
  }

  @Override
  public void hide() {
  }
}
