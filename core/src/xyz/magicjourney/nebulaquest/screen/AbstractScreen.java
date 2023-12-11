package xyz.magicjourney.nebulaquest.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 *  Pre-configured game screen with scene to draw and fixed aspect ratio.
 */
public abstract class AbstractScreen implements Screen {
  protected Stage stage;
  protected FitViewport viewport;
  protected AssetManager assets;

  public AbstractScreen(SpriteBatch batch, AssetManager assets) {
    this.viewport = new FitViewport(1920,1080);
    this.stage = new Stage(this.viewport, batch);  
    this.assets = assets;
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

  protected BitmapFont loadFont(String path, int size, Color color) {
    FreeTypeFontParameter parameters = new FreeTypeFontParameter();

    parameters.size = size;
    parameters.color = color;

    return this.loadFont(path, parameters);
  }

  protected BitmapFont loadFont(String path, FreeTypeFontParameter parameters) {
    FreeTypeFontGenerator generator = assets.get(path, FreeTypeFontGenerator.class);

    return generator.generateFont(parameters);
  }
}
