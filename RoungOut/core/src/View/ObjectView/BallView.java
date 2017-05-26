package View.ObjectView;

import IViews.IViews;
import Model.GameObjects.Ball;
import Model.GameObjects.Board;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by DukeA on 2017-04-28.
 */
public class BallView implements IViews {

    private Board board;
    private ShapeRenderer shapeRenderer;
    private int WIDTH;
    private int HEIGHT;

    float maxBallDistance;

    public BallView(int WIDTH, int HEIGHT, ShapeRenderer renderer,Board board) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.shapeRenderer = renderer;
        this.board = board;
        maxBallDistance = board.getRadius() * 0.75f;
    }

    @Override
    public void render(float delta) {
        for (Ball ball: board.getBalls()) {
            update(delta);

            // Default draw radius is ball's radius.
            float drawRadius = ball.getRadius();
            float distance = board.distanceFromCenter(ball);
            if (distance > maxBallDistance) {
                // If ball past a certain distance, reduce it's size.
                drawRadius = ball.getRadius() * (float)Math.pow(maxBallDistance / distance, 2);
            }
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.RED);
//<<<<<<< HEAD
            shapeRenderer.ellipse(
                    ball.getX() - drawRadius,
                    ball.getY() - drawRadius,
                    drawRadius * 2,
                    drawRadius * 2);
/*=======
            shapeRenderer.ellipse(ball.getX()
                    , ball.getY()
                    , ball.getRadius() * ((WIDTH / 2) / (HEIGHT / 2))
                    , ball.getRadius() * ((WIDTH / 2) / (HEIGHT / 2)));
>>>>>>> Change_Controller*/
            shapeRenderer.end();
        }
    }

    @Override
    public void reSize(int width, int height) {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    @Override
    public void update(float delta) {

    }
}
