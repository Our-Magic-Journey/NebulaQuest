package xyz.magicjourney.nebulaquest.assets;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.ray3k.stripe.FreeTypeSkinLoader;

import xyz.magicjourney.nebulaquest.event.Event;
import xyz.magicjourney.nebulaquest.event.EventGetter;

public class AssetsLoader {
  protected AssetManager assetManager;
  protected ArrayList<Loader> loaders;
  protected FileHandleResolver resolver;
  protected Event onLoad;
  protected boolean buildMode;

  public AssetsLoader(AssetManager assetManager) {
    this.assetManager = assetManager;
    this.resolver = new InternalFileHandleResolver();
    this.onLoad = new Event();
    
    this.loaders = new ArrayList<>();

    this.loadAssetManagerPlugins();
    this.loadLoaders();
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
    this.loaders.add(new Loader(new String[] { ".atlas" }, TextureAtlas.class));
  }

  public void loadAllAssets() {
    for (String file : this.getAllFiles()) {
      this.loadFile(file.trim());
    }
  }

  public void update() {
    if (assetManager.update()) {
      onLoad.emit();
      onLoad.unsubscribeAll();
    }
  }

  private String[] getAllFiles() {
    FileHandle handle = Gdx.files.internal("assets.txt");
    String text = "";


    try {
      text = handle.readString();      
    }
    catch (GdxRuntimeException e) {
      System.out.println("Cannot load asset list!");
      e.printStackTrace();
    }

    return text.split("\n");
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

  public EventGetter onLoad() {
    return onLoad;
  }
}
