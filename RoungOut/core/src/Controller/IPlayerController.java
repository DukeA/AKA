package Controller;

import View.IView; // The controller needs to get subs from the views

/**
 * Created by Alex on 2017-04-28.
 */
public interface IPlayerController {

    String latestKeyPressed();
    boolean addListener(IView view);
    boolean removeListener(IView view);
}
