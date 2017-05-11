package prototype.src.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Roungout.TITLE + " v " + Roungout.VERSION;
		config.width =Roungout.WIDTH;
		config.height=Roungout.HEIGHT;
		config.resizable = Roungout.RESIZE;
		//config.fullscreen = true;
		new LwjglApplication(new Roungout(), config);
	}


}
