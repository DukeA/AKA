package Tests;

import Model.GameObjects.Board;
import Model.GameObjects.IBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author DukeA
 * @author Ken
 */
public class BoardTest {

    Board board;

    public static final int WIDTH =  1000;
    public static final int HEIGHT = 1000;

    @BeforeEach
    public void beforeEach(){
        board = new Board(WIDTH, HEIGHT);
    }

    @Test
    public void getters() {
        Assertions.assertEquals(board.getXPos(), WIDTH /2);
        Assertions.assertEquals(board.getYPos(), HEIGHT /2);
        Assertions.assertEquals(board.getRadius(),
                //this seems to be a faulty formula
                //(float) (HEIGHT/2 + Math.pow(WIDTH,2)/8*HEIGHT));
                HEIGHT/2+100);
    }
    @Test
    public void boardTest(){
        Assertions.assertEquals(board.getYPos(), WIDTH /2);
        Assertions.assertEquals(board.getXPos(), HEIGHT /2);
        IBoard nboard = new Board(WIDTH, HEIGHT);
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
