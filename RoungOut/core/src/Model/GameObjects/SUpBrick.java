package Model.GameObjects;
/**
 * @author DukeA
 * Created on 2017-05-12.
 */
public class SUpBrick extends Brick {

    private Enum value;
    private  float SpeedValue;


    /**
     * Constructor
     * @param xPos X-Position
     * @param yPos Y-Position
     * @param width Width of the brick
     * @param height Height of the brick
     */
    public SUpBrick(float xPos, float yPos, float width, float height) {
        super(xPos, yPos, width, height);
        this.value = BrickType.SPEED_UP_BALL;
    }

    /**
     * Setter, increments the speedValue by 10
     */
    public void setSpeed() {
        if (this.equals(null)) {
            this.SpeedValue=+10;
        }
    }

    /**
     * Getter, gets the speed
     * @return The Speed
     */
    public float getSpeed() {
        return this.SpeedValue;
    }
}
