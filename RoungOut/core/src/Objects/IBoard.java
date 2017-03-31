package Objects;

/**
 * Created by Adam on 2017-03-30.
 */
public interface IBoard {
    public void create(float WIDTH ,float HEIGHT);
    public void dispose();
    public void render();
    public void resize(int width, int height);
}
