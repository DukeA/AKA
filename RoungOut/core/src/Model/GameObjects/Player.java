package Model.GameObjects;
import java.util.ArrayList;

/**
 *  @author Adam
 *  Created on 2017-04-04.
 *  @author Alex
 *  Heavly modified on 2017-04-24
 */
public class Player implements IPlayer {
    private Pad pad;
    private int points;

    /**
     * Default Constructor
     */
    public Player(){
        //Default values for a Player's pad
        pad = new Pad(30,20,0,0,0,0,0);
        points = 0;
    }

    /**
     * Overloaded constructor with specific parameters, points allways starts at 0
     * @param PadL The pad's length
     * @param PadW The pad's height
     * @param originX X-Position of the point the pad rotates around
     * @param originY Y-Position of the point the pad rotates around
     * @param PadXPos X-Position of the pad
     * @param PadYPos Y-Position of the pad
     * @param PadSpeed Speed of the pad
     */
    public Player(float PadL, float PadW,float originX, float originY, float PadXPos, float PadYPos, float PadSpeed) {
        pad = new Pad(PadL, PadW, originX, originY, PadXPos, PadYPos, PadSpeed);
        points = 0;
    }

    /**
     * Overloaded constructor, creates the player with a pre-existing pad
     * @param pad
     */
    public Player(Pad pad){
        this.pad = pad;
        this.points = 0;
    }
    /**
     *  Clone Constructor
     */
    public Player(Player player){
        this.pad = player.pad;
        this.points = 0;
    }

    /**
     * Setter, set the pads cordinamtes
     * @param x X-Position
     * @param y Y-Position
     */
    public void setPadCoordinates(float x, float y) {
      pad.getBody().setX(x);
      pad.getBody().setY(y);
    }

    /**
     * Getter, gets the X&Y Position
     * @return Returns an ArrayList with the [X,Y]-Position
     */
    public ArrayList<Float> getPadCoordinates() {
        ArrayList<Float> coordinateList = new ArrayList<Float>();
        coordinateList.add(0,pad.getBody().getX());
        coordinateList.add(1,pad.getBody().getY());
        return coordinateList;
    }

    /**
     * Setter, sets the pad
     * @param pad The Pad
     */
    public void setPad(Pad pad) {
        this.pad = pad;
    }

    /**
     * Getter, gets the pad
     * @return The Pad
     */
    @Override
    public Pad getPad() {
        return this.pad;
    }

    /**
     * Move the pad to the left (using the method in the Pad)
     */
    @Override
    public void moveLeft() {
        pad.padMoveLeft();
    }

    /**
     * Move the pad to the left (using the method in the Pad)
     */
    @Override
    public void moveRight() {
        pad.padMoveRight();
    }

    /**
     * Getter, get the points of the player
     * @return The Points
     */
    @Override
    public int getPoints() {
        return this.points;
    }

    /**
     * Setter, sets the points
     * @param points The Points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Increments the score by 1
     */
    public void offsetPoints() {points = points +1;} //increment by 1

    /**
     * Increments the score with a given value
     * @param value
     */
    public void offsetPoints(int value) {points = points + value;} //offsets by a given value

}
