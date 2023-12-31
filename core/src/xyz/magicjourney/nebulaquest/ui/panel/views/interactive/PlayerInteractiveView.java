package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractView;

public class PlayerInteractiveView extends AbstractView {
  protected Label label;

  public PlayerInteractiveView(AssetManager assets) {
    super(assets);

    this.label = new Label("Player View", skin, "small");
    this.add(label);
  }
}