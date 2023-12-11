package xyz.magicjourney.nebulaquest.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainMenu extends AbstractScreen {
  protected Table layout;

  public MainMenu(SpriteBatch batch, AssetManager assets) {
    super(batch, assets);
  }
  
  @Override
  public void show() {
    super.show();

    create();
  }
  
  protected void create() {
    layout = new Table();
    layout.setBackground(new TextureRegionDrawable(assets.get("./images/background.png", Texture.class)));
    layout.setFillParent(true);

    stage.addActor(layout);
  }
}
