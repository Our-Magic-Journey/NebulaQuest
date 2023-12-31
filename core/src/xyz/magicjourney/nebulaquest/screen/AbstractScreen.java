package xyz.magicjourney.nebulaquest.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import xyz.magicjourney.nebulaquest.music.MusicManager;

public abstract class AbstractScreen implements Screen {
  protected Stage stage;
  protected FitViewport viewport;
  protected AssetManager assets;
  protected ScreenManager screenManager;
  protected MusicManager musicManager;

  public AbstractScreen(SpriteBatch batch, AssetManager assets, ScreenManager screenManager, MusicManager musicManager) {
    this.viewport = new FitViewport(960, 540);
    this.stage = new Stage(this.viewport, batch);  
    this.assets = assets;
    this.screenManager = screenManager;
    this.musicManager = musicManager;
  }

  @Override
  public void show() {    
    // Redirects all user input (key pressing, mouse clicking ect.) to the screen's stage. 
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void render(float delta) {    
    this.update(delta);
    stage.draw();
  }

  protected void update(float delta) {
    stage.act(delta);
  }

  @Override
  public void resize(int width, int height) {
    this.viewport.update(width, height);
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void hide() {}

  @Override
  public void dispose() {
    this.stage.dispose();
  }
}
