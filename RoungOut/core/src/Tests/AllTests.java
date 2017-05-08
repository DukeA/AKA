package Tests;

/**
 * Created by Alex on 2017-04-07.
 * Updated by Ken on 2017-04-12.
 */
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;

public class AllTests {
    @BeforeAll
    public static void setup(){} //empty so we guaranteed start fresh

    /*
    Every time you add a testCase you need to add the following code
    inorder for the suit to test it:

    @Nested
    public class Extend(YOUR TEST HERE) extends Tests.(YOUR TEST HERE) {}

    what it does is nesting your test with this test suit (specifically AllTests), then
    when you run the suit your test will also be executed
    */

    @Nested
    public class ExtendBallTest extends Tests.BallTest {}

    @Nested
    public class ExtendPadTest extends Tests.PadTest {}

    @Nested
    public class ExtendPlayerTest extends Tests.PlayerTest {}

    @Nested
    public class ExtendBrickTest extends Tests.BrickTest {}

    @Nested
    public class ExtendBoardTest extends Tests.BoardTest {}

    @Nested
    public class ExtendCircleBodyTest extends Tests.CircleBodyTest {}

    @Nested
    public class ExtendRectangleBodyTest extends Tests.RectangleBodyTest {}

    @Nested
    public class ExtendLocationTest extends Tests.LocationTest {}

    @Nested
    public class ExtendCollisionTest extends Tests.CollisionTest {}

}