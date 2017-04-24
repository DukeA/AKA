package Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

/**
 * Created by DukeA on 2017-04-04.
 */
public class Player {
    private Pad pad;
    private float PadLength = 30;
    private float PadWidth = 30;
    private int points;

    public Player(){
        pad = new Pad(PadLength,PadWidth,30,30,30);
        points = 0;
    }

    //Overloaded with specific parameters
    public Player(float PadL, float PadW, double PadXPos, double PadYPos, float PadSpeed){
        pad = new Pad(PadL,PadW,PadXPos,PadYPos,PadSpeed);
        points = 0;
    }

    public void setPadCoordinates(float x, float y) {
      pad.setPadXPos(x);
      pad.setPadYPos(y);
    }
    public ArrayList<Double> getPadCoordinates() {
        ArrayList<Double> coordinateList = new ArrayList<Double>();
        coordinateList.add(0,pad.getPadXPos());
        coordinateList.add(1,pad.getPadYPos());
        return coordinateList;
    }
    public Pad getPad() {
        return this.pad;
    }
    public void movePad() {
         this.pad.PadMove(pad.getPadXPos()+pad.getPadSpeed(),pad.getPadYPos()+pad.getPadSpeed());
    }



    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPad(Pad pad) {
        this.pad = pad;
    }

}
