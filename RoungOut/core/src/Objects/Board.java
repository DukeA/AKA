package Objects;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Adam on 2017-03-29.
 */
public class Board implements IBoard  {


    private float xPos;
    private float yPos;
    private float radius;
    private ShapeRenderer renderer;

    public Board(float Width, float Height) {
        xPos =Width/2;
        yPos = Height/2;
        radius = 10000;
    }

    @Override
    public void create(float WIDTH, float HEIGHT) {
        renderer = new ShapeRenderer();
        xPos = getxPos();
        yPos = getYPos();
        radius = getRadius();

    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.begin();
        renderer.setColor(Color.GRAY);
        renderer.circle(getxPos(), getYPos(),getRadius());
        renderer.end();
    }

    @Override
    public void resize(int width, int height) {

    }
    public float getxPos() {
        return this.xPos;
    }
    public float getYPos() {
        return this.yPos;
    }
    public float getRadius() {
        return this.getRadius();
    }
}
