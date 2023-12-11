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
    this.titleFont = this.loadFont("fonts/MOOD MKII.ttf", 60);
    this.textFont = this.loadFont("fonts/MiniMOOD.ttf", 25);
    this.smallTextFont = this.loadFont("fonts/MiniMOOD.ttf", 20);
  }

  protected BitmapFont loadFont(String path, int size) {
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
    FreeTypeFontParameter parameters = new FreeTypeFontParameter();

    parameters.size = size;

    return generator.generateFont(parameters);
  }

  protected void create() {
    layout = new Table();
    layout.pad(10, 50, 10, 50);
    layout.setBackground(new TextureRegionDrawable(backgroundTexture));
    layout.setFillParent(true);
    
    title = new Label("Nebula Quest", new LabelStyle(titleFont, new Color(0xff7b00ff)));
    layout.add(title).expandY().center();


    ProgressBarStyle progressBarStyle = new ProgressBarStyle();
    progressBarStyle.background = Shape.rect((int)viewport.getWorldWidth(), 50, new Color(0xffffffff));
    progressBarStyle.knob = Shape.rect(0, 50, new Color(0xff7b00ff)); 
    progressBarStyle.knobBefore = Shape.rect((int)viewport.getWorldWidth(), 50, new Color(0xff7b00ff)); 

    Table loadingLayout = new Table();
    layout.row();
    layout.add(loadingLayout).expand().fill();

    progressBar = new ProgressBar(0, 100, 1, false, progressBarStyle);
    loadingLayout.add(progressBar).expand().fillX();

    Table detailsLayout = new Table();
    loadingLayout.row();
    loadingLayout.add(detailsLayout).bottom().left();

    loadingProgress = new Label("File 0/0", new LabelStyle(textFont, new Color(0xffffffff)));
    detailsLayout.add(loadingProgress).expandX().left();

    loadingDetails = new Label("Loading", new LabelStyle(smallTextFont, new Color(0xffffffff)));
    detailsLayout.row();
    detailsLayout.add(loadingDetails).expandX().left();


    stage.addActor(layout);
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
