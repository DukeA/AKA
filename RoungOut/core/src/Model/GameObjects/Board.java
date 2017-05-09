package Model.GameObjects;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adam on 2017-03-29.
 */
public class Board implements IBoard {


    private float xPos;
    private float yPos;
    private float radius;

    private Set<Ball> balls;
    private Set<Brick> bricks;
    private Set<Pad> pads;

    public Board(int Width, int Height) {
        xPos = Width/2;
        yPos = Height/2;
        radius = (float) Math.sqrt(Math.pow((Width/4),2)+Math.pow((Height/4),2));
        balls = new HashSet<Ball>();
        bricks = new HashSet<Brick>();
        pads = new HashSet<Pad>();
    }

    public float getXPos() {
        return this.xPos;
    }
    public float getYPos() {
        return this.yPos;
    }
    public float getRadius() {
        return this.radius;
    }

    public Set<Ball> getBalls() {
        return null;
    }

    public Set<Brick> getBricks() {
        return null;
    }

    public Set<Pad> getPads() {
        return null;
    }
}
