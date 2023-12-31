package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractView;

public class PlayersInteractiveView extends AbstractView {
  protected Label label;

  public PlayersInteractiveView(AssetManager assets) {
    super(assets);

    this.label = new Label("Players View", skin, "small");
    this.content.add(label);
  }

  @Override
  public void show() {
  }

  @Override
  public void hide() {
  }
}
