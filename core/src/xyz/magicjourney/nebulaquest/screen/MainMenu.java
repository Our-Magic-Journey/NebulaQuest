package xyz.magicjourney.nebulaquest.screen;

import java.util.LinkedHashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import xyz.magicjourney.nebulaquest.listener.Listener;
import xyz.magicjourney.nebulaquest.music.MusicManager;

public class MainMenu extends AbstractScreen {
  protected Music music;
  protected Table layout;
  protected Table buttons;
  protected Skin skin;
  protected Label title;
  protected LinkedHashMap<String, TextButton> options;

  public MainMenu(SpriteBatch batch, AssetManager assets, ScreenManager screenManager, MusicManager musicManager) {
    super(batch, assets, screenManager, musicManager);
    options = new LinkedHashMap<>();
  }
  
  @Override
  public void show() {
    super.show();
    options.clear();
    musicManager.playMenuMusic();

    layout();
  }
  
  protected void layout() {
    skin = assets.get("./skin/ui.skin.json");
    layout = new Table(skin);
    buttons = new Table(skin);

    title = new Label("Nebula Quest", skin, "title");
    options.put("New", new TextButton("New", skin));
    options.put("Continue", new TextButton("Continue", skin));
    options.put("Options", new TextButton("Options", skin));
    options.put("Credits", new TextButton("Credits", skin));
    options.put("Exit", new TextButton("Exit", skin));
    
    layout.setBackground(new TextureRegionDrawable(assets.get("./images/background.png", Texture.class)));
    layout.setFillParent(true);

    layout.add(title).expand().center();
    layout.row();

    layout.add(buttons).expand().fill().bottom();
    options.forEach((key, button) -> buttons.add(button).row());

    options.get("New").addListener(new Listener((event, actor) -> musicManager.playGameMusic()));
    options.get("Continue").addListener(new Listener((event, actor) -> musicManager.playGameMusic()));
    options.get("Credits").addListener(new Listener((event, actor) -> screenManager.select("credits")));
    options.get("Exit").addListener(new Listener((event, actor) -> Gdx.app.exit()));

    stage.addActor(layout);
  }

  @Override
  public void hide() {
  }
}
