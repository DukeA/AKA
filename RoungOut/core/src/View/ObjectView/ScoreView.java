package View.ObjectView;

import Model.GameObjects.Board;
import Model.GameObjects.IBoard;
import Model.GameObjects.IPlayer;
import Model.GameObjects.Player;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DukeA on 2017-05-06.
 */
public class ScoreView implements  IViews{

    private final int WIDTH;
    private final int HEIGHT;
    private SpriteBatch spriteBatch;
    private Board player;
    private BitmapFont font;
    private BitmapFont font2;

    public ScoreView(int Width, int Height,SpriteBatch spriteBatch) {
        this.WIDTH = Width;
        this.HEIGHT = Height;
        this.player = new Board(Width,Height);
        this.spriteBatch = spriteBatch;
        this.font =  new BitmapFont();
        font.setColor(Color.BLACK);
        this.font2 = new BitmapFont();
        font2.setColor(Color.BLACK);
        player.addPad(new Player(80f, 30f, WIDTH / 2 - 350, HEIGHT / 2, 0));
        player.addPad(new Player(80f, 30f, WIDTH / 2 - 450, HEIGHT / 2, 0));



    }
    public void render(float delta) {
        List<Player> players = new ArrayList<Player>(player.getPlayers());

                String playerPoints = String.valueOf(players.get(0).getPoints());
                String player2Points = String.valueOf(players.get(1).getPoints());
                spriteBatch.begin();
                font.draw(spriteBatch,"Player 1: " +playerPoints,100,HEIGHT-100);
                font.getData().setScale(2);
                font2.draw(spriteBatch,"Player 2: " +player2Points ,WIDTH-200,HEIGHT-100);
                font2.getData().setScale(2);
                spriteBatch.end();




    }

    @Override
    public void reSize(int width, int height) {

    }

    public void dispose() {

    }

    @Override
    public void update(float delta) {

    }

    public void resize(int width, int height) {


    }
    public int getScore(Player player) {
        return player.getPoints();
    }

}
