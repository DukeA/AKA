package View.ObjectView;

import IViews.IViews;
import Model.GameObjects.Board;
import Model.GameObjects.Pad;
import Model.GameObjects.Player;
import Utils.Vector;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.Array;
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
        List<Player> players = new ArrayList<Player>(Pads.getPlayers());
        drawPad(players.get(0),Color.BLUE);
        drawPad(players.get(1),Color.LIME);
    }

    public void drawPad(Player p, Color color) {
            Vector vector1 = new Vector(p.getPad().getPadXPos()
                    , p.getPad().getPadYPos()
                    , p.getPad().getOriginX()
                    , p.getPad().getOriginY());
            Vector vector2 = vector1.crossProudct();
            vector2 = vector2.normalization();
            Vector vectorLength = vector2.vectorLength(p.getPad().getLength());
            vector1 = vector1.normalization();
            Vector vectorWidth = vector1.vectorWidth(p.getPad().getWidth());
            float[] shape = {
                    p.getPad().getPadXPos() + vectorLength.getxPos() + vectorWidth.getxPos(),
                    p.getPad().getPadYPos() + vectorLength.getyPos() + vectorWidth.getyPos(),
                    p.getPad().getPadXPos() + vectorLength.getxPos() - vectorWidth.getxPos(),
                    p.getPad().getPadYPos() + vectorLength.getyPos() - vectorWidth.getyPos(),
                    p.getPad().getPadXPos() - vectorLength.getxPos() - vectorWidth.getxPos(),
                    p.getPad().getPadYPos() - vectorLength.getyPos() - vectorWidth.getyPos(),
                    p.getPad().getPadXPos() - vectorLength.getxPos() + vectorWidth.getxPos(),
                    p.getPad().getPadYPos() - vectorLength.getyPos() + vectorWidth.getyPos(),
            };
            Pixmap pixmap = new Pixmap(1,1,Pixmap.Format.RGB888);
            pixmap.setColor(color);
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





    public void reSize(int width, int height) {

    }

    public void dispose() {
        polygonSpriteBatch.dispose();
    }

    @Override
    public void update(float delta) {

    }

}
