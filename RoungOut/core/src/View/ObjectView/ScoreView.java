package View.ObjectView;

import Model.GameObjects.IScore;
import Model.GameObjects.Player;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * Created by DukeA on 2017-05-06.
 */
public class ScoreView implements IScore{

    private final int WIDTH;
    private final int HEIGHT;
    private Label playerLabel;
    private Label player2Label;
    private Player player;
    private int player1Points;
    private Player player2;
    private int player2Points;


    public ScoreView(int Width, int Height,ShapeRenderer shapeRenderer) {
        this.WIDTH = Width;
        this.HEIGHT = Height;
        this.player = new Player();
        this.player2 = new Player();
        shapeRenderer = new ShapeRenderer();
    }
    public void render(float delta) {


    }
    public void update(float delta) {

    }
    public void dispose() {

    }
    public void resize(int width, int height) {


    }

    @Override
    public int getScore(Player p1) {
        return p1.getPoints();
    }
}
