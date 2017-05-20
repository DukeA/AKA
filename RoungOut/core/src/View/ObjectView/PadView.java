package View.ObjectView;

import Model.GameObjects.Board;
import Model.GameObjects.IPlayer;
import Model.GameObjects.Player;
import Utils.Vector;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.ShortArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DukeA on 2017-05-02.
 */
public class PadView implements IViews {

    private Board Pads;
    private PolygonSpriteBatch polygonSpriteBatch;
    private Texture solidTexture;
    private PolygonSprite polySprite;

    private int WIDTH;
    private int HEIGHT;


    public PadView(int width, int height, PolygonSpriteBatch polygonSpriteBatch, Board board) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.polygonSpriteBatch = polygonSpriteBatch;
        this.Pads = board;

    }

    public void render(float delta) {
        drawPad();
    }

    public void drawPad() {
        for (Player player : Pads.getPlayers()) {
            Vector vector1 = new Vector(player.getPad().getPadXPos()
                    , player.getPad().getPadYPos()
                    , player.getPad().getOriginX()
                    , player.getPad().getOriginY());
            Vector vector2 = vector1.crossProudct();
            vector2 = vector2.normalization();
            Vector vectorLength = vector2.vectorLength(player.getPad().getLength());
            vector1 = vector1.normalization();
            Vector vectorWidth = vector1.vectorWidth(player.getPad().getWidth());
            float[] shape = {
                    player.getPad().getPadXPos() + vectorLength.getxPos() + vectorWidth.getxPos(),
                    player.getPad().getPadYPos() + vectorLength.getyPos() + vectorWidth.getyPos(),
                    player.getPad().getPadXPos() + vectorLength.getxPos() - vectorWidth.getxPos(),
                    player.getPad().getPadYPos() + vectorLength.getyPos() - vectorWidth.getyPos(),
                    player.getPad().getPadXPos() - vectorLength.getxPos() - vectorWidth.getxPos(),
                    player.getPad().getPadYPos() - vectorLength.getyPos() - vectorWidth.getyPos(),
                    player.getPad().getPadXPos() - vectorLength.getxPos() + vectorWidth.getxPos(),
                    player.getPad().getPadYPos() - vectorLength.getyPos() + vectorWidth.getyPos(),
            };
            Pixmap pixmap = new Pixmap(1,1,Pixmap.Format.RGB888);
            pixmap.setColor(Color.BLUE);
            pixmap.fill();
            solidTexture = new Texture(pixmap);
            TextureRegion textureRegion = new TextureRegion(solidTexture);
            EarClippingTriangulator triangulator = new EarClippingTriangulator();
            ShortArray tringle = triangulator.computeTriangles(shape);

            PolygonRegion polyreg = new PolygonRegion(textureRegion,shape,tringle.toArray());
            polySprite = new PolygonSprite(polyreg);

            polygonSpriteBatch.begin();
            polySprite.draw(polygonSpriteBatch);
            polygonSpriteBatch.end();

        }
    }


    public void reSize(int width, int height) {

    }

    public void dispose() {
        polygonSpriteBatch.dispose();
    }

    @Override
    public void update(float delta) {

    }

}
