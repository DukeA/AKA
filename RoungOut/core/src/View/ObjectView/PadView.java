package View.ObjectView;

import Model.GameObjects.IPlayer;
import Model.GameObjects.Pad;
import Model.GameObjects.Player;
import View.IView;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by DukeA on 2017-05-02.
 */
public class PadView implements  IViews, IView {

    private IPlayer pad;
    private IPlayer pad2;
    private ShapeRenderer shapeRenderer2;
    private ShapeRenderer shapeRenderer;
    private int WIDTH;
    private int HEIGHT;


    public PadView(int width, int height, ShapeRenderer renderer) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.shapeRenderer2 = renderer;
        this.shapeRenderer = renderer;
        pad =  new Player(80f,30f,WIDTH / 2 - 350, HEIGHT / 2,0);
        pad2 = new Player(80f,30f,WIDTH / 2 - 450, HEIGHT / 2,0);

    }

    public void render(float delta) {

        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(pad.getPad().getPadXPos()
                , pad.getPad().getPadYPos()
                , pad.getPad().getWidth()
                , pad.getPad().getLength());
        shapeRenderer.end();

        shapeRenderer2.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer2.setColor(Color.LIME);
        shapeRenderer2.rect(pad2.getPad().getPadXPos()
                , pad2.getPad().getPadYPos()
                , pad2.getPad().getWidth()
                , pad2.getPad().getLength());
        shapeRenderer2.end();
    }


    public void reSize(int width, int height) {

    }

    public void dispose() {
        shapeRenderer.dispose();
    }


    @Override
    public void update() {

    }
}
