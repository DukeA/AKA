package View.ObjectView;

import Model.GameObjects.IPad;
import Model.GameObjects.IPlayer;
import Model.GameObjects.Pad;
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
public class PadView implements  IPad {

    private Pad pad;
    private Pad pad2;
    private Stage stage;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private ShapeRenderer shapeRenderer2;
    private int WIDTH;
    private int HEIGHT;


    public PadView(int width, int height,SpriteBatch batch,ShapeRenderer renderer) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.batch = batch;
        this.shapeRenderer = renderer;
        this.shapeRenderer2 = renderer;
        pad = createPad(WIDTH / 2 - 350, HEIGHT / 2);
        pad2 = createPad(WIDTH / 2 - 450, HEIGHT / 2);

    }

    public void render(float delta) {

        update(delta);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect((float) pad.getPadXPos()
                , (float) pad.getPadYPos()
                , (float) pad.getWidth()
                , (float) pad.getLength());

        shapeRenderer.setColor(Color.LIME);
        shapeRenderer.rect((float) pad2.getPadXPos()
                , (float) pad2.getPadYPos()
                , (float) pad2.getWidth()
                , (float) pad2.getLength());
        shapeRenderer.end();
    }

    public void update(float delta) {

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
}
