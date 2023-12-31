package xyz.magicjourney.nebulaquest.ui.panel.views;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class AbstractView extends Group {
  protected Table content;
  protected Skin skin;

  public abstract void show();
  public abstract void hide();

  public AbstractView(AssetManager assets) {
    this.skin = assets.get("skin/ui.skin.json", Skin.class);
    this.content = new Table(skin);
    this.content.setFillParent(true);
    this.addActor(this.content);
  }
}
