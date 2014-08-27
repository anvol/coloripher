package com.anvol.coloripher.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.anvol.coloripher.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        //config.fullscreen = true;
        config.resizable = true;
        config.width = 800;
        config.height = 480;
        config.vSyncEnabled = true;

		new LwjglApplication(new MyGdxGame(), config);
	}
}
