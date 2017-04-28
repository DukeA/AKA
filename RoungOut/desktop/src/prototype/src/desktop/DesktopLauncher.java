package prototype.src.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "ROUND OUT";
		config.useGL30 = false;
		config.height = 1080;
		config.width = 1980;
		config.resizable = false;
		//config.fullscreen = true;
		new LwjglApplication(new Roungout(), config);
	}


}
