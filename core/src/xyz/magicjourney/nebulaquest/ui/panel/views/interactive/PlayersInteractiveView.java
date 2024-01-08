package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import xyz.magicjourney.nebulaquest.ui.panel.InteractivePanel;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;

import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractInteractiveView;

public class PlayersInteractiveView extends AbstractInteractiveView {
  protected Label label;

  public PlayersInteractiveView(AssetManager assets, InteractivePanel parent, TourPanel tourPanel) {
    super(assets, parent, tourPanel);

    this.label = new Label("Players View", skin, "small");
    this.add(label);
  }
}
