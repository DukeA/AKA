package View.ObjectView;

import Model.GameObjects.IPad;
import Model.GameObjects.IPlayer;
import Model.GameObjects.Pad;
import View.IView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by DukeA on 2017-05-02.
 */
public class PadView implements IPad, IViews, IView {

    private Pad pad;
    private Pad pad2;
    private ShapeRenderer shapeRenderer2;
    private ShapeRenderer shapeRenderer;
    private int WIDTH;
    private int HEIGHT;


    public PadView(int width, int height, ShapeRenderer renderer) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.shapeRenderer2 = renderer;
        this.shapeRenderer = renderer;
        pad = createPad(WIDTH / 2 - 350, HEIGHT / 2);
        pad2 = createPad(WIDTH / 2 - 450, HEIGHT / 2);

    }

    public void render(float delta) {

        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(pad.getPadXPos()
                , pad.getPadYPos()
                , pad.getWidth()
                , pad.getLength());
        shapeRenderer.end();

        shapeRenderer2.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer2.setColor(Color.LIME);
        shapeRenderer2.rect(pad2.getPadXPos()
                , pad2.getPadYPos()
                , pad2.getWidth()
                , pad2.getLength());
        shapeRenderer2.end();
    }

    @Override
    public void update(float delta) {
        this.update();
    }

    public void reSize(int width, int height) {

    }

    public void dispose() {
        shapeRenderer.dispose();
    }

    @Override
    public Pad createPad(int xPos, int yPos) {
        return new Pad(80f, 15f, xPos, yPos, 0);
    }

    @Override
    public void update() {



    }
}
