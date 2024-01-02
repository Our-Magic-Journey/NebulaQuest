package xyz.magicjourney.nebulaquest.ui.panel.views;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import xyz.magicjourney.nebulaquest.ui.panel.ViewPanel;

public abstract class AbstractView extends Table {
  protected Skin skin;
  protected ViewPanel<?> parent;

  public AbstractView(AssetManager assets, ViewPanel<?> parent) {
    super(assets.get("skin/ui.skin.json", Skin.class));
    this.skin = this.getSkin();
    this.parent = parent;
  }
}
