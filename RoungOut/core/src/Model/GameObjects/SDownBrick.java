package Model.GameObjects;

/**
 * @author DukeA
 * Created on 2017-05-12.
 */
public class SDownBrick  extends Brick {

    private  Enum value;
    private float speedValue;

    /**
     * Constructor
     * @param xPos X-Position
     * @param yPos Y-Position
     * @param width Width of the brick
     * @param height Height of the brick
     */
    public SDownBrick(float xPos, float yPos, float width, float height) {
        super(xPos,yPos, width,height);
        this.value = BrickType.SLOW_DOWN_BALL;

    }

    /**
     * Setter, decreases the speed value by -5
     */
    public void setSpeed() {
        if (this.equals(null)) {
            this.speedValue=-5;
        }
    }

    /**
     * Getter, gets the speed value
     * @return The speed value
     */
    public float getSpeed() {
        return this.speedValue;
    }
}
