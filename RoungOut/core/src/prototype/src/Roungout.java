package prototype.src;

import Objects.Board;
import Objects.IBoard;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Roungout extends ApplicationAdapter {

    private boolean Pause;

    private static final String Tag = Roungout.class.getName();

    private LevelRenderer levelRenderer;
    private LevelController levelController;


    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        levelController = new LevelController();
        levelRenderer = new LevelRenderer(levelController);
        Pause = false;
    }

    public void render() {
        if (!Pause) {
            levelController.update(Gdx.graphics.getDeltaTime());
        }
        levelController.update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        levelRenderer.render();
    }

    public void resize(int height, int width) {
        levelRenderer.reszie(width, height);
    }

    public void pause() {
        Pause = true;

    }

    public void resume() {
        Pause = false;
    }

    public void dispose() {
        levelRenderer.dispose();
    }


}
