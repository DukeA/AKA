package View.ObjectView;


import Controller.IPlayerController;
import Controller.PlayerController;
import Model.GameObjects.Board;
import Model.GameObjects.IBoard;
import View.IView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by DukeA on 2017-04-28.
 */
public class BoardView implements Screen, IView {
   private ArrayList<IViews> views;
    private Stage stage;
    private IBoard board;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private IPlayerController controller;
    private final int WIDTH;
    private final int HEIGHT;


    public BoardView(int WIDTH,int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        views = new ArrayList<IViews>();
        views.add(0,createPad(WIDTH,HEIGHT,shapeRenderer));
        views.add(1,createBall(WIDTH,HEIGHT,shapeRenderer));
        views.add(2,createBricks(WIDTH,HEIGHT,shapeRenderer));
        views.add(3,createScorePad(WIDTH,HEIGHT,batch));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update();

        board =new Board(WIDTH, HEIGHT);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        stage = new Stage(new ExtendViewport(WIDTH,HEIGHT));
        Gdx.gl.glLineWidth(16);

        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.ellipse(WIDTH/4,5,
                board.getRadius()*(WIDTH/4)/(HEIGHT/4)
                , (board.getRadius()*(WIDTH/4)/(HEIGHT/4)));
        shapeRenderer.end();

        for (int i =0; i<views.size(); i++) {
            views.get(i).render(delta);
        }
        stage.draw();
        batch.end();



    }
    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        this.update(delta);

    }

    public void update(float delta) {
        for (int i =0; i<views.size(); i++) {
            views.get(i).update(delta);
        }
    }

    @Override
    public void resize(int width, int height) {

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

        shapeRenderer.dispose();
    }
    public BallView createBall(int xPos, int yPos,  ShapeRenderer renderer) {
        return new BallView(WIDTH,HEIGHT,renderer);
    }
    public PadView createPad(int xPos, int yPos, ShapeRenderer renderer) {

        return new PadView(WIDTH,HEIGHT,renderer);
    }
    public BrickView createBricks(int xPos, int yPos, ShapeRenderer renderer) {
        return new BrickView(WIDTH,HEIGHT,renderer);
    }
    public ScoreView createScorePad(int xPos, int yPos,SpriteBatch spriteBatch ) {
        return new ScoreView(WIDTH,HEIGHT,spriteBatch);
    }

}
