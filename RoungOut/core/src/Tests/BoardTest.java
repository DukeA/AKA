package Tests;

import Model.ViewObjects.Board;
import Model.ViewObjects.IBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by DukeA on 2017-04-29.
 */
public class BoardTest {

    IBoard board;

      int Width = 100;
      int Height =100;

    @BeforeEach
    public void beforeEach(){
        board = new Board(Width,Height);
    }

    @Test
    public void getters() {
        Assertions.assertEquals(board.getXPos(),Width/2);
        Assertions.assertEquals(board.getYPos(),Height/2);
        Assertions.assertEquals(board.getRadius(),
                //this seems to be a faulty formula
                //(float) (Height/2 + Math.pow(Width,2)/8*Height));
        (float) Math.sqrt(Math.pow((Width/4),2)+Math.pow((Height/4),2)));
    }
    @Test
    public void boardTest(){
        Assertions.assertEquals(board.getYPos(),Width/2);
        Assertions.assertEquals(board.getXPos(),Height/2);
        IBoard nboard = new Board(Width,Height);
        Assertions.assertNotSame(board,nboard);
        Assertions.assertEquals(board.getXPos(),nboard.getXPos());
        Assertions.assertEquals(board.getYPos(),nboard.getYPos());
    }
    @Test
    public void radiusTest() {
        Assertions.assertEquals(board.getRadius()< board.getXPos()/2,
                board.getRadius()<board.getYPos()/2);
    }
}
