package View.ObjectView;

import Model.GameObjects.Board;
import Model.GameObjects.IPlayer;
import Model.GameObjects.Player;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by DukeA on 2017-05-02.
 */
public class PadView implements IViews {

    private Board Pads;
    private ShapeRenderer shapeRenderer2;
    private ShapeRenderer shapeRenderer;
    private int WIDTH;
    private int HEIGHT;


    public PadView(int width, int height, ShapeRenderer renderer) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.shapeRenderer2 = renderer;
        this.shapeRenderer = renderer;
        Pads = new Board(WIDTH, HEIGHT);
        Pads.addPad(new Player(80f, 30f, WIDTH / 2 - 350, HEIGHT / 2, 0));
        Pads.addPad(new Player(80f, 30f, WIDTH / 2 - 450, HEIGHT / 2, 0));

    }

    public void render(float delta) {
        for (Player p : Pads.getPlayers()) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.rect(p.getPad().getPadXPos(),
                    p.getPad().getPadYPos(),
                    p.getPad().getWidth(),
                    p.getPad().getLength());
            shapeRenderer.end();
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
