package xyz.magicjourney.nebulaquest.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import xyz.magicjourney.nebulaquest.NebulaQuest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Nebula Quest");
		config.useVsync(true);
		config.setForegroundFPS(60);

		new Lwjgl3Application(new NebulaQuest(), config);
	}
}
