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
    private Player player2;
    private int player2Points;
    private BitmapFont font;
    private BitmapFont font2;

    public ScoreView(int Width, int Height, ShapeRenderer shapeRenderer) {
        this.WIDTH = Width;
        this.HEIGHT = Height;
        this.player = new Player();
        this.player2 = new Player();
        this.shapeRenderer = shapeRenderer;
        this.font =  new BitmapFont();
        font.setColor(Color.WHITE);
        this.font2 = new BitmapFont();
        font2.setColor(Color.WHITE);



    }
    public void render(float delta) {
        update(delta);

        String player1points = String.valueOf(player1Points);
        String player2points = String.valueOf(player2Points);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(WIDTH-300,HEIGHT-100,200,50);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(20,HEIGHT-100,200,50);
        shapeRenderer.end();
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
