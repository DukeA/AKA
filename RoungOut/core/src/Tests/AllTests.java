package Tests;

/**
 * Created by Alex on 2017-04-07.
 */

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;

public class AllTests {
    @BeforeAll
    public static void setup(){} //empty

    @Nested
    public class ExtendBallTest extends BallTest {}

    @Nested
    public class ExtendMovePadTest extends MovePadTest {}

}