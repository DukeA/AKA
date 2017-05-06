package View.ObjectView;

import Model.GameObjects.IScore;
import Model.GameObjects.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by DukeA on 2017-05-06.
 */
public class ScoreView implements IScore{

    private final int WIDTH;
    private final int HEIGHT;
    private Label playerLabel;
    private Label player2Label;
    private Skin skins;
    private Player player;
    private int player1Points;
    private Player player2;
    private int player2Points;


    public ScoreView(int Width, int Height) {
        this.WIDTH = Width;
        this.HEIGHT = Height;
        this.player = new Player();
        this.player2 = new Player();
    }
    public void render(float delta) {
        update(delta);
        this.skins =new Skin(Gdx.files.internal(""));


        String value = String.valueOf(player1Points);
        playerLabel = new Label("Player1:  " + value,skins);

        String value2 = String.valueOf(player2Points);
        player2Label = new Label("Player2:  " + value2, skins);

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
