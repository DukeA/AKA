package Controller;

import View.ObjectView.IViews;

/**
 * Created by Alex on 2017-04-28.
 */
public interface IPlayerController {

    String latestKeyPressed();
    boolean addListener(IViews view);
    boolean removeListener(IViews view);
}
