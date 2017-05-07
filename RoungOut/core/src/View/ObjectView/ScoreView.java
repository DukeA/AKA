package View.ObjectView;

import Model.GameObjects.IScore;
import Model.GameObjects.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import javafx.scene.text.Font;

/**
 * Created by DukeA on 2017-05-06.
 */
public class ScoreView implements IScore{

    private final int WIDTH;
    private final int HEIGHT;
    private ShapeRenderer shapeRenderer;
    private Player player;
    private int player1Points;
    private SpriteBatch batch;
    private Player player2;
    private int player2Points;
    private  TextField player1Field;
    private TextField player2Field;
    private Stage stage;
    private Skin skin;
    private Table table;

    public ScoreView(int Width, int Height, ShapeRenderer shapeRenderer) {
        this.WIDTH = Width;
        this.HEIGHT = Height;
        this.player = new Player();
        this.player2 = new Player();
        this.shapeRenderer = shapeRenderer;
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));



    }
    public void render(float delta,Stage stage, SpriteBatch batch) {
        update(delta);
        this.batch =batch;
        this.stage = stage;
        this.table = new Table(skin);
        String player1points = String.valueOf(player1Points);
        String player2points = String.valueOf(player2Points);
        player2Field = new TextField("Player 2: " +player2points,skin);
        player1Field =new TextField("Player 1: " + player1points,skin);
        table.add(player2Field).setActorBounds(WIDTH/2,HEIGHT/2,30,30);
        table.add(player1Field).setActorBounds(WIDTH/2,HEIGHT/2,30,30);
        
        stage.addActor(table);




    }
    public void update(float delta) {
        player1Points = getScore(player);
        player2Points = getScore(player2);
    }
    public void dispose() {

    }
    public void resize(int width, int height) {


    }

    @Override
    public int getScore(Player player) {
        return player.getPoints();
    }
}
