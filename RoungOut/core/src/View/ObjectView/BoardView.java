package View.ObjectView;

import AbstractGameComponents.AGame;
import IViews.IViews;
import Model.GameObjects.Board;
import Model.GameObjects.IBoard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;


/**
 * Created by DukeA on 2017-04-28.
 */
public class BoardView  implements IViews,Screen {
   private ArrayList<IViews> views;
    private IBoard board;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private PolygonSpriteBatch polygonSprite;
    private AGame game;

    //private IPlayerController controller;

    private final int WIDTH;
    private final int HEIGHT;


    public BoardView(int WIDTH,int HEIGHT, AGame game) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.game = game;
        batch = new SpriteBatch();
        polygonSprite = new PolygonSpriteBatch();
        shapeRenderer = new ShapeRenderer();

        this.board=game.getBoard();
        //this.board = new Board(WIDTH,HEIGHT); //
               views = new ArrayList<IViews>();
        views.add(0,createPad(WIDTH,HEIGHT,polygonSprite,(Board)board));
        views.add(1,createBall(WIDTH,HEIGHT,shapeRenderer,(Board)board));
        views.add(2,createBricks(WIDTH,HEIGHT,shapeRenderer,(Board)board));
        views.add(3,createScorePad(WIDTH,HEIGHT,batch,(Board)board));

    }

    @Override
    public void show() {
    Gdx.input.setInputProcessor(game.getGameController());
        //GIVE BACK CONTROLLER HERE
    }

    @Override
    public void render(float delta) {
        //Clear the screen, and add background
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        Gdx.gl.glLineWidth(16);

        float Width =board.getRadius()*(WIDTH/2)/(HEIGHT/2);
        float Height =board.getRadius()*(WIDTH/2)/(HEIGHT/2);
        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.ellipse(WIDTH/2-Width/2,HEIGHT/2-Height/2,
                Width, Height);
        shapeRenderer.end();
        batch.end();
        for (int i =0; i<views.size(); i++) {
            views.get(i).render(delta);
        }
        


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void reSize(int width, int height) {
        this.resize(width,height);
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

    @Override
    public void update(float delta) {
        game.getGameController().atRequest();
        board.update(delta);

        for (IViews views: views) {
            views.update(delta);
        }
    }

    public BallView createBall(int xPos, int yPos,  ShapeRenderer renderer, Board board) {
        return new BallView(WIDTH,HEIGHT,renderer,board);
    }
    public PadView createPad(int xPos, int yPos, PolygonSpriteBatch renderer,Board board) {

        return new PadView(WIDTH,HEIGHT,renderer,board);
    }
    public BrickView createBricks(int xPos, int yPos, ShapeRenderer renderer,Board board) {
        return new BrickView(WIDTH,HEIGHT,renderer,board);
    }
    public ScoreView createScorePad(int xPos, int yPos,SpriteBatch spriteBatch,Board board ) {
        return new ScoreView(WIDTH,HEIGHT,spriteBatch,board);
    }

}
