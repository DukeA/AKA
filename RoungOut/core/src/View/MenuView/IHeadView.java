package View.MenuView;

import Model.GameObjects.Board;
import View.MenuView.OptionView;
import View.ObjectView.BoardView;

/**
 * Created by DukeA on 2017-05-05.
 */
public interface IHeadView {
    public BoardView createBoardView(int HEIGHT, int WIDTH, Board board);
    public OptionView createOptionView(int HEIGHT, int WIDTH);
}
