import javafx.scene.canvas.Canvas;
import javafx.scene.shape.*;



/**
 * Created by DukeA on 2017-03-21.
 */
public class Player extends Canvas {


    private  boolean checkInsert;

    public Player(double xpostion, double ypostion) {
        Rectangle p =  new Rectangle();
        p.setX(xpostion);
        p.setY(ypostion);
        p.setWidth(30);
        p.setHeight(30);
        p.setArcHeight(40);
        p.setArcWidth(40);


    }




}
