package Model.GameObjects;

import Model.GameObjects.Physics.RectangleBody;

/**
 * Created by DukeA on 2017-05-12.
 */
public class SUpBrick extends Brick {

    private Enum value;
    private  float SpeedValue;


    public SUpBrick(float xPos, float yPos, float width, float height) {
        super(xPos, yPos, width, height);
        this.value = BrickType.SPEED_UP_BALL;
    }

    public void setSpeed() {
        if (this.equals(null)) {
            this.SpeedValue=+100;
        }
    }
    public float getSpeedValue() {
        return this.SpeedValue;
    }
    //TODO What else needs to be here?
}
