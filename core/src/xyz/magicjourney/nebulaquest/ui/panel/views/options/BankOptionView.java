package xyz.magicjourney.nebulaquest.ui.panel.views.options;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import xyz.magicjourney.nebulaquest.ui.panel.ViewPanel;
import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractView;

public class BankOptionView extends AbstractView {
  protected Label label;

  public BankOptionView(AssetManager assets, ViewPanel<?> parent) {
    super(assets, parent);

    this.label = new Label("Bank View", skin, "small");
    this.add(label);
  }
}