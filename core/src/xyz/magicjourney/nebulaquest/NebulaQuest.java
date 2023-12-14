package xyz.magicjourney.nebulaquest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import xyz.magicjourney.nebulaquest.assets.AssetsLoader;
import xyz.magicjourney.nebulaquest.music.MusicManager;
import xyz.magicjourney.nebulaquest.screen.CreditsScreen;
import xyz.magicjourney.nebulaquest.screen.LoadingScreen;
import xyz.magicjourney.nebulaquest.screen.MainMenu;
import xyz.magicjourney.nebulaquest.screen.ScreenManager;

public class NebulaQuest extends Game {
	private SpriteBatch batch;
	private AssetManager assets;
  protected AssetsLoader assetsLoader;
	protected ScreenManager screenManager;
	protected MusicManager musicManager;


	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.assets = new AssetManager();
		this.assetsLoader = new AssetsLoader(assets);
		this.musicManager = new MusicManager();
		this.screenManager = new ScreenManager(this, batch, assets, musicManager);

		this.screenManager.register("main-menu", MainMenu::new);
		this.screenManager.register("loading", LoadingScreen::new);
		this.screenManager.register("credits", CreditsScreen::new);

		this.assetsLoader.onLoad().subscribe(this::onLoad);
		this.screenManager.select("loading");
		this.assetsLoader.loadAllAssets();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		assetsLoader.update();

		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	protected boolean isRunningOnAndroid() {
		return Gdx.app.getType() == ApplicationType.Android;
	}

	public void reloadAssets() {
		this.assetsLoader.onLoad().subscribe(() -> setScreen(this.getScreen()));
		this.assetsLoader.loadAllAssets();
		this.screenManager.select("loading");;
	}

	protected void onLoad() {
		musicManager.register(assets.get("./music/styl1.music.ogg"));
		musicManager.register(assets.get("./music/styl2.music.ogg"));
		musicManager.register(assets.get("./music/styl3.music.ogg"));
		musicManager.setMenuMusic(assets.get("./music/menu.music.ogg"));
		screenManager.select("main-menu");
	}
}
