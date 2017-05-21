package IViews;

/**
 * Created by DukeA on 2017-05-04.
 */
public interface IViews {


    public void render(float delta);
    public void reSize(int width,int height);
    public void dispose();
    public void update(float delta);
}
