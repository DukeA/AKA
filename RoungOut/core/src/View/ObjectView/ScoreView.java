package View.ObjectView;

import Model.GameObjects.IPlayer;
import Model.GameObjects.Player;
import View.IView;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by DukeA on 2017-05-06.
 */
public class ScoreView implements  IViews, IView{

    private final int WIDTH;
    private final int HEIGHT;
    private SpriteBatch spriteBatch;
    private IPlayer player;
    private IPlayer player2;
    private BitmapFont font;
    private BitmapFont font2;

    public ScoreView(int Width, int Height,SpriteBatch spriteBatch) {
        this.WIDTH = Width;
        this.HEIGHT = Height;
        this.player = new Player();
        this.player2 = new Player();
        this.spriteBatch = spriteBatch;
        this.font =  new BitmapFont();
        font.setColor(Color.BLACK);
        this.font2 = new BitmapFont();
        font2.setColor(Color.BLACK);



    }
    public void render(float delta) {

        String player1points = String.valueOf(player.getPoints());
        String player2points = String.valueOf(player.getPoints());
        spriteBatch.begin();
        font.draw(spriteBatch,"Player 1: " +player1points,100,HEIGHT-100);
        font.getData().setScale(2);
        font2.draw(spriteBatch,"Player 2: " +player2points,WIDTH-300,HEIGHT-100);
        font2.getData().setScale(2);
        spriteBatch.end();
    }

    @Override
    public void reSize(int width, int height) {

    }

    public void dispose() {

    }
    public void resize(int width, int height) {


    }
    public int getScore(Player player) {
        return player.getPoints();
    }

    @Override
    public void update() {

    }
}
