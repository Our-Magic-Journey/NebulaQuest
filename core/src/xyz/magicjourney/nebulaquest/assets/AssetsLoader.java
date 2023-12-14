package xyz.magicjourney.nebulaquest.assets;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.ray3k.stripe.FreeTypeSkinLoader;

import xyz.magicjourney.nebulaquest.event.Event;
import xyz.magicjourney.nebulaquest.event.EventGetter;
import xyz.magicjourney.nebulaquest.logger.AssetsLoadingLogger;

public class AssetsLoader {
  protected AssetManager assetManager;
  protected ArrayList<Loader> loaders;
  protected FileHandleResolver resolver;
  protected AssetsLoadingLogger logger;
  protected Event onLoad;
  protected boolean buildMode;

  public AssetsLoader() {
    this.assetManager = new AssetManager();
    this.resolver = new InternalFileHandleResolver();
    this.onLoad = new Event();
    
    this.buildMode = this.isRunningInBuildMode();
    this.logger = new AssetsLoadingLogger();
    this.loaders = new ArrayList<>();
    this.assetManager.setLogger(logger);

    this.loadAssetManagerPlugins();
    this.loadLoaders();
  }
 
  protected boolean isRunningInBuildMode() {
    return Gdx.files.internal("./assets").exists();
  }

  protected void loadAssetManagerPlugins() {
    this.assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
    this.assetManager.setLoader(Skin.class, new FreeTypeSkinLoader(assetManager.getFileHandleResolver()));
    this.assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
  }

  protected void loadLoaders() {
    this.loaders.add(new Loader(new String[] { ".png", ".jpg" }, Texture.class));
    this.loaders.add(new Loader(new String[] { ".music.mp3", ".music.ogg", ".music.wav" }, Music.class));
    this.loaders.add(new Loader(new String[] { ".mp3", ".ogg", ".wav" }, Sound.class));
    this.loaders.add(new Loader(new String[] { ".ttf" }, FreeTypeFontGenerator.class));
    this.loaders.add(new Loader(new String[] { ".skin.json" }, Skin.class));
  }

  public void loadAllAssets() {
    List<String> files = this.getAllFiles(resolver.resolve("."));

    for (String file : files) {
      this.loadFile(file);
    }
  }

  public void update() {
    if (assetManager.update()) {
      onLoad.emit();
      onLoad.unsubscribeAll();
    }
  }

  private ArrayList<String> getAllFiles(FileHandle folder) {
    ArrayList<String> paths = new ArrayList<>();

    for (FileHandle file : folder.list()) {
      if (!this.isAsset(file.path())) {
        continue;
      }

      if (file.isDirectory()) {
        paths.addAll(getAllFiles(resolver.resolve(file.path())));
      }
      else {
        paths.add(exitAssetFolder(file.path()));
      }
    }

    return paths;
  }

  private void loadFile(String file) {
    System.out.println("Loading " + file);

    for (Loader loader : loaders) {
      if (loader.load(assetManager, file)) {
        return;
      }
    }

    System.out.println("There is no file loader for: " + file);
  }

  protected boolean isAsset(String path) {
    return !buildMode || path.contains("./assets");
  }

  protected String exitAssetFolder(String path) {
    if (buildMode) {
      return path.replace("./assets/", "");
    }

    return path;
  }

  public EventGetter onLoad() {
    return onLoad;
  }

  /** @param fileName the asset file name
   * @return the asset
   * @throws GdxRuntimeException if the asset is not loaded */
	public synchronized <T> T get (String fileName) {
		return assetManager.get(this.matchAsset(fileName));
	}

	/** @param fileName the asset file name
	 * @param type the asset type
	 * @return the asset
	 * @throws GdxRuntimeException if the asset is not loaded */
	public synchronized <T> T get (String fileName, Class<T> type) {
		return assetManager.get(this.matchAsset(fileName), type, true);
	}

  protected String matchAsset(String path) {
    if (!buildMode) {
      return "./" + path;
    }

    return path;
  }

  public float getLoadingProgress() {
    return this.assetManager.getProgress() * 100;
  }

  public String getLoadingProgressAsText() {
    int max = assetManager.getQueuedAssets() + assetManager.getLoadedAssets();
    int value = assetManager.getLoadedAssets();

    return String.format("%d/%d", value, max);
  }

  public String getLastLoadedAssetName() {
    return this.logger.getLastLoaded();
  }
}
