package prototype.src.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import prototype.src.Roungout;

public class DesktopLauncher {


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "ROUND OUT";
		config.useGL30 = false;
		config.width = 600;
		config.height = 780;
		new LwjglApplication(new Roungout(), config);
	}
}
