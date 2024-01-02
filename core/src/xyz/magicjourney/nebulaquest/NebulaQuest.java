package xyz.magicjourney.nebulaquest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import xyz.magicjourney.nebulaquest.assets.AssetsLoader;
import xyz.magicjourney.nebulaquest.music.MusicManager;
import xyz.magicjourney.nebulaquest.screen.CreditsScreen;
import xyz.magicjourney.nebulaquest.screen.GameScreen;
import xyz.magicjourney.nebulaquest.screen.LoadingScreen;
import xyz.magicjourney.nebulaquest.screen.MainMenu;
import xyz.magicjourney.nebulaquest.screen.ScreenManager;

public class NebulaQuest extends Game {
	private SpriteBatch batch;
	private AssetsLoader assetsLoader;
	protected AssetManager assetManager;
	protected ScreenManager screenManager;
	protected MusicManager musicManager;

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.assetManager = new AssetManager();
		this.assetsLoader = new AssetsLoader(this.assetManager);
		this.musicManager = new MusicManager();
		this.screenManager = new ScreenManager(this, batch, assetManager, musicManager);

		this.screenManager.register("main-menu", MainMenu::new);
		this.screenManager.register("loading", LoadingScreen::new);
		this.screenManager.register("credits", CreditsScreen::new);
		this.screenManager.register("game", GameScreen::new);

		this.assetsLoader.onLoad().subscribe(this.onLoad);
		this.screenManager.select("loading");
		this.assetsLoader.loadAllAssets();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		this.assetsLoader.update();

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

	protected Runnable onLoad = () -> {
		this.musicManager.register(this.assetManager.get("music/music4.music.ogg"));
		this.musicManager.register(this.assetManager.get("music/music1.music.ogg"));
		this.musicManager.register(this.assetManager.get("music/music2.music.ogg"));
		this.musicManager.register(this.assetManager.get("music/music3.music.ogg"));
		this.musicManager.register(this.assetManager.get("music/music5.music.ogg"));
		this.musicManager.setMenuMusic(this.assetManager.get("music/menu.music.ogg"));
		this.screenManager.select("main-menu");
	};
}
