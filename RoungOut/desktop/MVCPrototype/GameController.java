package MVCPrototype;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kendu on 2017-04-27.
 */
public class GameController {

    // Enums //////////////////////////////////////////////////////////////////
    public enum GameState {
        SHOW_START_SCREEN, GAME_IN_PROGRESS
    }

    // Defaults ///////////////////////////////////////////////////////////////
    public static final double DEFAULT_BOARD_RADIUS = 100.0f;   // ~100%
    public static final GameState DEFAULT_GAME_STATE = GameState.GAME_IN_PROGRESS;

    // Static variables ///////////////////////////////////////////////////////
    private static GameController controller;

    // Instance variables /////////////////////////////////////////////////////
    private Set<View> views;
    private GameBoard board;
    private GameState gameState;

    // Singleton Constructor //////////////////////////////////////////////////
    private GameController() {
        views = new HashSet<View>();
        board = new GameBoard(DEFAULT_BOARD_RADIUS);
        gameState = DEFAULT_GAME_STATE;
    }

    // Static methods /////////////////////////////////////////////////////////
    public static GameController getInstance() {
        if (controller == null) {
            controller = new GameController();
        }
        return controller;
    }

    // Instance methods ///////////////////////////////////////////////////////
    public void addView(View view) {
        if (view != null) {
            views.add(view);
        }
    }

    public void removeView(View view) {
        views.remove(view);
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        if (board != null) {
            this.board = board;
        }
    }

    public void update() {

        // If game is playing
        if (gameState == GameState.GAME_IN_PROGRESS) {
            board.updateBoard();
            for (View view : views) {
                view.updateView();
            }
        }

    }

}
