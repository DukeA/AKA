package Controller;

/**
 * Created by Alex on 2017-04-28.
 */
public interface IPlayerController {

    String latestKeyPressed();
    boolean addListener(IView view);
    boolean removeListener(IView view);
}
