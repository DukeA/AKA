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

      float Width = 100;
      float Height =100;

    @BeforeEach
    public void beforeEach(){
        board = new Board(Width,Height);
    }

    @Test
    public void getters() {
        Assertions.assertEquals(board.getxPos(),Width/2);
        Assertions.assertEquals(board.getYPos(),Height/2);
        Assertions.assertEquals(board.getRadius(),
                (float) (Height/2 + Math.pow(Width,2)/8*Height));
    }
    @Test
    public void boardTest(){
        Assertions.assertEquals(board.getYPos(),Width/2);
        Assertions.assertEquals(board.getxPos(),Height/2);
        IBoard nboard = new Board(Width,Height);
        Assertions.assertNotSame(board,nboard);
        Assertions.assertEquals(board.getxPos(),nboard.getxPos());
        Assertions.assertEquals(board.getYPos(),nboard.getYPos());
    }
    @Test
    public void radiusTest() {
        Assertions.assertEquals(board.getRadius()< board.getxPos()/2,
                board.getRadius()<board.getYPos()/2);
    }
}
