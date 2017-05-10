package View.ObjectView;

import Model.GameObjects.Brick;
import Model.GameObjects.Physics.RectangleBody;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

/**
 * Created by DukeA on 2017-05-06.
 */
public class BrickView implements  IViews  {


    private int WIDTH;
    private int HEIGHT;
    private ShapeRenderer shapeRenderer;
    private ArrayList<RectangleBody> bricks;



    public BrickView(int Width, int Height, ShapeRenderer renderer) {
        this.WIDTH = Width;
        this.HEIGHT = Height;
        this.shapeRenderer = renderer;
        bricks = new ArrayList<RectangleBody>();
        for(int i =0; i > 9; i++)  {
            bricks.add(i,createBrick(Width/2+10, HEIGHT/2+10) );
        }

    }

    public void render(float delta) {
        for (int i =0; i>bricks.size();i++) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(bricks.get(i).getX(),bricks.get(i).getY(),
                    bricks.get(i).getWidth(),bricks.get(i).getHeight());
            shapeRenderer.end();
        }
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void reSize(int width, int height) {

    }

    @Override
    public void dispose() {

    }

    public RectangleBody createBrick(int xpos,int yPos) {
        return new RectangleBody(xpos,yPos,30,30);
    }

}
