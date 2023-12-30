package xyz.magicjourney.nebulaquest.ui.panel;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.ray3k.tenpatch.TenPatchDrawable;

public class Panel extends Group {
  protected Table content;
  protected TenPatchDrawable texture;
  protected Skin skin;

  public Panel(int width, int height, AssetManager assets) {
    super();
    
    this.skin = assets.get("skin/ui.skin.json", Skin.class);

    this.texture = this.skin.get("panel10", TenPatchDrawable.class);
    this.content = new Table(skin);

    this.setWidth(width);
    this.setHeight(height);
    this.content.setFillParent(true);
    this.content.setBackground(texture);
    this.addActor(this.content);
  }
}
