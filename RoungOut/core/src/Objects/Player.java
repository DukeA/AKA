package Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by DukeA on 2017-04-04.
 */
public class Player {

    private final int PADHEIGHT = 70;
    private final int PADWIDTH = 20;
    private final int PADXPOS = Gdx.graphics.getWidth() / 2 - 500;
    private final int PADYPOS = Gdx.graphics.getHeight() / 2;
    private final Color MyColor;

    private float x,y;
    private float speed = 60;
    private int points ;

    public Player(Color color) {
        x = Gdx.graphics.getWidth() / 2 - 500;
        y = Gdx.graphics.getHeight() / 2;
        points =0;
        MyColor = color;
    }

    public void update(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            x +=speed *delta;
            y +=speed *delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
            x-=speed*delta;
            y-=speed*delta;
        }
    }
    public int getPoints() {
        return this.points;
    }
    public void setPoints( int points) {
        this.points = points;
    }

    public void renderPad(Pad sb) {
        sb.set(ShapeRenderer.ShapeType.Filled);
        sb.begin();
        sb.setColor(MyColor);
    }




}
