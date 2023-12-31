package xyz.magicjourney.nebulaquest.ui.panel.views;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class AbstractView extends Table {
  protected Skin skin;

  public AbstractView(AssetManager assets) {
    super(assets.get("skin/ui.skin.json", Skin.class));
    this.skin = this.getSkin();
  }
}
