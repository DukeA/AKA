package Model.GameObjects;

import java.awt.image.ImageConsumer;
import java.util.ArrayList;

/**
 * Created by DukeA on 2017-04-04.
 * Modified by Alex on 2017-04-24
 */
public class Player implements IPlayer, IModel {
    private Pad pad;
    private int points;

    public Player(){
        //Default values for a Player's pad
        pad = new Pad(30,20,0,0,0,0,0);
        points = 0;
    }

    //Overloaded with specific parameters, points allways starts at 0
    public Player(float PadL, float PadW,float originX, float originY, float PadXPos, float PadYPos, float PadSpeed){
        pad = new Pad(PadL,PadW,originX,originY,PadXPos,PadYPos,PadSpeed);
        points = 0;
    }

    public void setPadCoordinates(float x, float y) {
      pad.getBody().setX(x);
      pad.getBody().setY(y);
    }
    public ArrayList<Float> getPadCoordinates() {
        ArrayList<Float> coordinateList = new ArrayList<Float>();
        coordinateList.add(0,pad.getBody().getX());
        coordinateList.add(1,pad.getBody().getY());
        return coordinateList;
    }

    public void setPad(Pad pad) {
        this.pad = pad;
    }

    @Override
    public Pad getPad() {
        return this.pad;
    }

    @Override
    public void moveLeft() {
        pad.padMoveLeft();
    }

    @Override
    public void moveRight() {
        pad.padMoveRight();
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void offsetPoints() {points = points +1;} //increment by 1

    public void offsetPoints(int value) {points = points + value;} //offsets by a given value

}
