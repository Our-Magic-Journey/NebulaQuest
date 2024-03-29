package xyz.magicjourney.nebulaquest.screen;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import xyz.magicjourney.nebulaquest.listener.Listener;
import xyz.magicjourney.nebulaquest.music.MusicManager;

public class CreditsScreen extends AbstractScreen {
  protected Table layout;
  protected Table authors;
  protected Skin skin;
  protected Label title;
  protected TextButton backButton;
  protected ArrayList<Label> labels;

  public CreditsScreen(SpriteBatch batch, AssetManager assets, ScreenManager screenManager, MusicManager musicManager) {
    super(batch, assets, screenManager, musicManager);
    this.labels = new ArrayList<>();
  }

    @Override
  public void show() {
    super.show();
    labels.clear();

    create();
  }

  protected void create() {
    skin = assets.get("skin/ui.skin.json");
    layout = new Table(skin);
    authors = new Table(skin);

    backButton = new TextButton("Back", skin, "text");
    title = new Label("Nebula Quest", skin, "title");
    labels.add(new Label("Dominik Purgal", skin));
    labels.add(new Label("Dominik Prabucki", skin));
    labels.add(new Label("Authors", skin, "medium-blue"));
    labels.add(new Label("Michal Zamorski", skin));
    labels.add(new Label("Sebastian Zienkiewicz", skin));

    layout.setBackground(new TextureRegionDrawable(assets.get("images/background.png", Texture.class)));
    layout.setFillParent(true);

    layout.add(title).expand().center();
    layout.row();

    layout.add(authors).expand().fill().bottom();

    labels.forEach((label) -> authors.add(label).row());

    backButton.addListener(new Listener((event, actor) -> screenManager.select("main-menu")));
    backButton.setPosition(20, 10);

    stage.addActor(layout);
    stage.addActor(backButton);
  }
}
