package View.ObjectView;

import Model.GameObjects.Brick;
import Model.GameObjects.Physics.RectangleBody;
import View.IView;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

/**
 * Created by DukeA on 2017-05-06.
 */
public class BrickView implements  IViews, IView {


    private int WIDTH;
    private int HEIGHT;
    private ShapeRenderer shapeRenderer;
    private ArrayList<RectangleBody> bricks;



    public BrickView(int Width, int Height, ShapeRenderer renderer) {
        this.WIDTH = Width;
        this.HEIGHT = Height;
        this.shapeRenderer = renderer;
        bricks = new ArrayList<RectangleBody>();
        bricks.add(0,createBrick(Width/2-40,Height/2));
        bricks.add(1,createBrick(Width/2,Height/2));
        bricks.add(2,createBrick(Width/2+40,Height/2));
        bricks.add(3,createBrick(Width/2-40,Height/2-40));
        bricks.add(4,createBrick(Width/2,Height/2-40));
        bricks.add(5,createBrick(Width/2+40,Height/2-40));
        bricks.add(6,createBrick(Width/2-40,Height/2+40));
        bricks.add(7,createBrick(Width/2,Height/2+40));
        bricks.add(8,createBrick(Width/2+40,Height/2+40));

    }

    public void render(float delta) {
        for (int i =0; i < bricks.size();i++) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(bricks.get(i).getX(),bricks.get(i).getY(),
                    bricks.get(i).getWidth(),bricks.get(i).getHeight());
            shapeRenderer.end();
        }
    }


    @Override
    public void reSize(int width, int height) {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();

    }

    public RectangleBody createBrick(int xpos,int yPos) {
        return new RectangleBody(xpos,yPos,30,30);
    }

    @Override
    public void update() {

    }
}
