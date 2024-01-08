package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import xyz.magicjourney.nebulaquest.ui.panel.InteractivePanel;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;
import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractInteractiveView;

public class BankInteractiveView extends AbstractInteractiveView {
  protected Label label;

  public BankInteractiveView(AssetManager assets, InteractivePanel parent, TourPanel tourPanel) {
    super(assets, parent, tourPanel);

    this.label = new Label("Bank View", skin, "small");
    this.add(label);
  }
}