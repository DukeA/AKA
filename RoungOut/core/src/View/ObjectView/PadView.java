package View.ObjectView;

import Model.GameObjects.Board;
import Model.GameObjects.IPlayer;
import Model.GameObjects.Player;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DukeA on 2017-05-02.
 */
public class PadView implements IViews {

    private Board Pads;
    private ShapeRenderer shapeRenderer2;
    private ShapeRenderer shapeRenderer;
    private int WIDTH;
    private int HEIGHT;


    public PadView(int width, int height, ShapeRenderer renderer, Board board) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.shapeRenderer2 = renderer;
        this.shapeRenderer = renderer;
        this.Pads = board;

    }

    public void render(float delta) {
        List<Player> p = new ArrayList<Player>(Pads.getPlayers());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(p.get(1).getPad().getPadXPos(),
                p.get(1).getPad().getPadYPos(),
                p.get(1).getPad().getOriginX(),
                p.get(1).getPad().getOriginY(),
                p.get(1).getPad().getWidth(),
                p.get(1).getPad().getLength(),
                0,0,
                p.get(1).getPad().getAngle());
        shapeRenderer.end();


        shapeRenderer2.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer2.rect(
                p.get(1).getPad().getPadXPos(),
                p.get(1).getPad().getPadYPos(),
                p.get(1).getPad().getOriginX(),
                p.get(1).getPad().getOriginY(),
                p.get(1).getPad().getWidth(),
                p.get(1).getPad().getLength(),
                0,0,
                p.get(1).getPad().getAngle());
        shapeRenderer2.end();
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
