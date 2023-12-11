package xyz.magicjourney.nebulaquest.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import xyz.magicjourney.nebulaquest.drawable.Shape;
import xyz.magicjourney.nebulaquest.logger.AssetsLoadingLogger;

public class LoadingScreen extends AbstractScreen {
  protected Texture backgroundTexture;
  protected BitmapFont titleFont;
  protected BitmapFont textFont;
  protected BitmapFont smallTextFont;
  protected AssetsLoadingLogger logger;
  
  protected Table layout;
  protected Label title;
  protected Label loadingDetails;
  protected Label loadingProgress;
  protected ProgressBar progressBar;


  public LoadingScreen(SpriteBatch batch, AssetManager assets) {
    super(batch, assets);

    this.logger = new AssetsLoadingLogger();
    this.assets.setLogger(logger);

    this.loadAssets();
    this.create();
  }

  protected void loadAssets() {
    this.backgroundTexture = new Texture(Gdx.files.internal("images/background.png"));
    this.titleFont = this.loadFont("fonts/MOOD MKII.ttf", 50);
    this.textFont = this.loadFont("fonts/MiniMOOD.ttf", 20);
    this.smallTextFont = this.loadFont("fonts/MiniMOOD.ttf", 15);
  }

  protected BitmapFont loadFont(String path, int size) {
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
    FreeTypeFontParameter parameters = new FreeTypeFontParameter();

    parameters.size = size;

    return generator.generateFont(parameters);
  }

  protected void create() {
    layout = new Table();
    layout.setBackground(new TextureRegionDrawable(backgroundTexture));
    layout.setFillParent(true);
    
    title = new Label("Nebula Quest", new LabelStyle(titleFont, new Color(0xff7b00ff)));
    title.setPosition(0, viewport.getWorldHeight() - title.getHeight() * 2);
    title.setWidth(viewport.getWorldWidth());
    title.setAlignment(Align.center);

    loadingProgress = new Label("File 0/0", new LabelStyle(textFont, new Color(0xffffffff)));
    loadingProgress.setPosition(50, 100);

    loadingDetails = new Label("Loading", new LabelStyle(smallTextFont, new Color(0xffffffff)));
    loadingDetails.setPosition(50, 70);

    float progressBarSize = viewport.getWorldWidth() - 200;
    ProgressBarStyle progressBarStyle = new ProgressBarStyle();
    progressBarStyle.background = Shape.rect((int)progressBarSize, 50, new Color(0xffffffff));
    progressBarStyle.knob = Shape.rect(0, 50, new Color(0xff7b00ff)); 
    progressBarStyle.knobBefore = Shape.rect((int)progressBarSize, 50, new Color(0xff7b00ff)); 

    progressBar = new ProgressBar(0, 100, 1, false, progressBarStyle);
    progressBar.setSize(progressBarSize, 100);
    progressBar.setPosition(100, 300);

    stage.addActor(layout);
    layout.addActor(title);    
    layout.addActor(loadingProgress);
    layout.addActor(loadingDetails);
    layout.addActor(progressBar);
  }

  @Override
  protected void update(float delta) {
    super.update(delta);

    loadingDetails.setText("Loaded: " + this.logger.getLastLoaded());
    System.out.println(this.logger.getLastLoaded());
    loadingProgress.setText(assets.getLoadedAssets() + "/" + (assets.getQueuedAssets() + assets.getLoadedAssets()));
    progressBar.setValue(assets.getProgress() * 100);
  }

  @Override
  public void dispose() {
    super.dispose();

    this.backgroundTexture.dispose();
    this.textFont.dispose();
    this.smallTextFont.dispose();
    this.titleFont.dispose();
  }
}
