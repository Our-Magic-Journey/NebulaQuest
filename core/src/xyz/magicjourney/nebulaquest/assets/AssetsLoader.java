package xyz.magicjourney.nebulaquest.assets;

import java.util.ArrayList;

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
import com.ray3k.stripe.FreeTypeSkinLoader;

import xyz.magicjourney.nebulaquest.event.Event;
import xyz.magicjourney.nebulaquest.event.EventGetter;

public class AssetsLoader {
  protected AssetManager assetManager;
  protected ArrayList<Loader> loaders;
  protected FileHandleResolver resolver;
  protected Event onLoad;

  public AssetsLoader(AssetManager assets) {
    this.assetManager = assets;
    this.resolver = new InternalFileHandleResolver();
    this.onLoad = new Event();
    
    this.loaders = new ArrayList<>();
    this.loaders.add(new Loader(new String[] { ".png", ".jpg" }, Texture.class));
    this.loaders.add(new Loader(new String[] { ".music.mp3", ".music.ogg", ".music.wav" }, Music.class));
    this.loaders.add(new Loader(new String[] { ".mp3", ".ogg", ".wav" }, Sound.class));
    this.loaders.add(new Loader(new String[] { ".ttf" }, FreeTypeFontGenerator.class));
    this.loaders.add(new Loader(new String[] { ".skin.json" }, Skin.class));

    // add ttf support to the AssetManager
    assets.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
    assets.setLoader(Skin.class, new FreeTypeSkinLoader(assetManager.getFileHandleResolver()));
    assets.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
  }

  public void loadAllAssets() {
    ArrayList<String> files = this.getAllFiles(resolver.resolve("."));

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
      if (file.isDirectory()) {
        paths.addAll(getAllFiles(resolver.resolve(file.path())));
      }
      else {
        paths.add(file.path());
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

  public EventGetter onLoad() {
    return onLoad;
  }
}
