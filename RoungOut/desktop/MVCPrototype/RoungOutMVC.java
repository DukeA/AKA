package MVCPrototype;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kendu on 2017-04-27.
 */
public class RoungOutMVC {

    public static final int UPDATE_FREQUENZY = 50;  // 50Hz

    protected GameController gameController;
    protected View textView;

    public static void main(String[] args) {
        new RoungOutMVC().main();
    }

    public void initialize() {
        gameController = GameController.getInstance();
        textView = new TextView();
        gameController.addView(textView);
    }

    public void main() {
        initialize();
        TimerTask timerTask = new GameTimerTask();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 1000/UPDATE_FREQUENZY);
    }

    private class GameTimerTask extends TimerTask {

        @Override
        public void run() {
            gameController.update();
        }
    }

}
