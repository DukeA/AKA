package View.ObjectView;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;

import prototype.src.desktop.Roungout;


/**
 * Created by DukeA on 2017-04-28.
 */
public class BoardView implements Screen {

    private final Roungout game;
    private com.badlogic.gdx.scenes.scene2d.Stage stage;

    private BallView ballView;

    public BoardView( final Roungout game){
        this.game = game;
        this.stage = new com.badlogic.gdx.scenes.scene2d.Stage(new FitViewport(Roungout.WIDTH,Roungout.WIDTH,game.camera));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();
    }
    public void update(float delta) {
            stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
