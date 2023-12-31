package xyz.magicjourney.nebulaquest.ui.panel.views.options;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractView;

public class PlayerOptionView extends AbstractView {
  protected Label label;

  public PlayerOptionView(AssetManager assets) {
    super(assets);

    this.label = new Label("Player View", skin, "small");
    this.content.add(label);
  }

  @Override
  public void show() {
  }

  @Override
  public void hide() {
  }
}