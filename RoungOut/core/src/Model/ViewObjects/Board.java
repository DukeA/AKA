package Model.ViewObjects;


import Model.ViewObjects.IBoard;

/**
 * Created by Adam on 2017-03-29.
 */
public class Board implements IBoard {


    private float xPos;
    private float yPos;
    private float radius;

    public Board(float Width, float Height) {
        xPos = Width/2;
        yPos = Height/2;
        radius = 530;
    }

    public float getxPos() {
        return this.xPos;
    }
    public float getYPos() {
        return this.yPos;
    }
    public float getRadius() {
        return this.radius;
    }
}
