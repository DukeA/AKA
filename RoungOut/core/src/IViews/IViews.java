package IViews;

/**
 * @author DukeA
 * Created on 2017-05-04.
 */
public interface IViews {
    /**
     * This interface is here som that the Controllers can call on some of the methods a Screen has it it needs to.
     * (Screen is part of the framework and handles visuals)
     */

    /**
     * A render method,
     * @param delta time between frames, comes from interface
     */
    public void render(float delta);

    /**
     * A Resize method
     * @param width width on screen
     * @param height height on screen
     */
    public void reSize(int width,int height);

    /**
     * Dispose method
     */
    public void dispose();

    /**
     *
     * @param delta time between frames, comes from interface
     */
    public void update(float delta);
}
