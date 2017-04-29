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
    float radius =;

    @BeforeEach
    public void beforeEach(){
        board = new Board(Width,Height);
    }

    @Test
    public void getters() {
        Assertions.assertEquals(board.getxPos(),Width/2);
        Assertions.assertEquals(board.getYPos(),Height/2);
    }
    @Test
    public void BoardTest(){

    }
}
