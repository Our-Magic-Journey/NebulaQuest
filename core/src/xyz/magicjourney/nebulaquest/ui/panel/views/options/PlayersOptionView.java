package xyz.magicjourney.nebulaquest.ui.panel.views.options;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import xyz.magicjourney.nebulaquest.ui.panel.ViewPanel;
import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractView;

public class PlayersOptionView extends AbstractView {
  protected Label label;

  public PlayersOptionView(AssetManager assets, ViewPanel<?> parent) {
    super(assets, parent);

    this.label = new Label("Players View", skin, "small");
    this.add(label);
  }
}
