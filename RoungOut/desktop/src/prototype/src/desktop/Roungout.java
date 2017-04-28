package prototype.src.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;


public class Roungout extends ApplicationAdapter {

    private boolean Pause;

    private static final String Tag = Roungout.class.getName();




    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Pause = false;
    }

    public void render() {
        if (!Pause) {

        }

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


    }

    public void resize(int height, int width) {

    }

    public void pause() {
        Pause = true;

    }
    public void resume() {
        Pause = false;
    }

    public void dispose() {

    }


}
