package View.ObjectView;

import Model.GameObjects.Board;
import Model.GameObjects.IPlayer;
import Model.GameObjects.Player;
import Utils.Vector;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DukeA on 2017-05-02.
 */
public class PadView implements IViews {

    private Board Pads;
    private ShapeRenderer shapeRenderer;
    private  ShapeRenderer shapeRenderer2;
    private Vector vector;
    private int WIDTH;
    private int HEIGHT;



    public PadView(int width, int height,ShapeRenderer shapeRenderer, Board board) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.shapeRenderer = shapeRenderer;
        this.shapeRenderer2 = shapeRenderer;
        this.Pads = board;

    }

    public void render(float delta) {
        drawPad();




    }
    public void drawPad() {
        for(Player player: Pads.getPlayers()) {
            Vector vector1 = new Vector(player.getPad().getPadXPos()
                                        ,player.getPad().getPadYPos()
                                        ,player.getPad().getOriginX()
                                        ,player.getPad().getOriginY());
        }



    }


    public void reSize(int width, int height) {

    }

    public void dispose() {
        shapeRenderer.dispose();
    }

    @Override
    public void update(float delta) {

    }

}
