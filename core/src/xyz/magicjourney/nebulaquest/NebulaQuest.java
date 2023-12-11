package xyz.magicjourney.nebulaquest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import xyz.magicjourney.nebulaquest.assets.AssetsLoader;
import xyz.magicjourney.nebulaquest.screen.LoadingScreen;
import xyz.magicjourney.nebulaquest.screen.MainMenu;

public class NebulaQuest extends Game {
	private SpriteBatch batch;
	private AssetManager assets;
  protected AssetsLoader assetsLoader;

	protected MainMenu mainMenu;
	protected LoadingScreen loadingScreen;

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.assets = new AssetManager();
		this.assetsLoader = new AssetsLoader(assets);

		this.mainMenu = new MainMenu(batch, assets);
		this.loadingScreen = new LoadingScreen(batch, assets);

		this.assetsLoader.onLoad().subscribe(() -> setScreen(mainMenu));
		this.setScreen(loadingScreen);
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
		this.setScreen(loadingScreen);
	}
}
