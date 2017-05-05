package prototype.src.desktop;

import View.MenuView.MenuView;
import View.MenuView.OptionView;
import View.ObjectView.BoardView;
import com.badlogic.gdx.Screen;

/**
 * Created by DukeA on 2017-05-02.
 */
public interface IScreen {

    public BoardView getBorderView();
    public MenuView getMenuView();
    public OptionView getOptionView();
}
