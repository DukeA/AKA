package View.ObjectView;

import IViews.IViews;
import Model.GameObjects.Ball;
import Model.GameObjects.Board;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * @author Adam
 * Created on 2017-04-28.
 */
public class BallView implements IViews {

    private Board board;
    private ShapeRenderer shapeRenderer;
    private int WIDTH;
    private int HEIGHT;
    float maxBallDistance;

    /**
     * The Constructor class for the ball view creates
     * a ball view for the  ball. Where the inParameters
     * for the constructor is the WIDTH and HEIGHT from
     * the screen. The Render function is a  reference
     * from the board view class which renders the shapeRenderer.
     * The board is a reference to the board class
     * @param WIDTH
     * @param HEIGHT
     * @param renderer
     * @param board
     */

    public BallView(int WIDTH, int HEIGHT, ShapeRenderer renderer,Board board) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.shapeRenderer = renderer;
        this.board = board;
        maxBallDistance = board.getRadius() * 0.75f;
    }

    /**
     * render class renders
     * the movement for ball ,
     * and also how the ball should move on the screen
     * which takes the values of the ball and move them to another location.
     * @param delta
     */
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
            shapeRenderer.ellipse(
                    ball.getX() - drawRadius,
                    ball.getY() - drawRadius,
                    drawRadius * 2,
                    drawRadius * 2);
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
