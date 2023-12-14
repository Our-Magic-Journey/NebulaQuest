package xyz.magicjourney.nebulaquest.screen;

import java.util.HashSet;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import xyz.magicjourney.nebulaquest.listener.Listener;


public class CreditsScreen extends AbstractScreen {
  protected Table layout;
  protected Table authors;
  protected Skin skin;
  protected Label title;
  protected TextButton back;
  protected HashSet<Label> labels;


  public CreditsScreen(SpriteBatch batch, AssetManager assets, ScreenManager screenManager) {
    super(batch, assets, screenManager);

    this.labels = new HashSet<>();
  }

    @Override
  public void show() {
    super.show();
    labels.clear();

    create();
  }
  
  protected void create() {
    skin = assets.get("./skin/ui.skin.json");
    layout = new Table(skin);
    authors = new Table(skin);

    title = new Label("Nebula Quest", skin, "title");
    back = new TextButton("Back", skin);
    labels.add(new Label("Authors", skin, "medium-blue"));
    labels.add(new Label("Dominik Purgal", skin));
    labels.add(new Label("Dominik Prabucki", skin));
    labels.add(new Label("Michał Zamorski", skin));
    labels.add(new Label("Sebastian Zienkiewicz", skin));
    
    layout.setBackground(new TextureRegionDrawable(assets.get("./images/background.png", Texture.class)));
    layout.setFillParent(true);

    layout.add(title).expand().center();
    layout.row();

    layout.add(authors).expand().fill().bottom();
    labels.forEach((button) -> authors.add(button).row());

    back.setPosition(10, 10);
    back.addListener(new Listener((event, actor) -> screenManager.select("main-menu")));

    stage.addActor(layout);
    stage.addActor(back);
  }
}
