package Model.GameObjects.Physics;

/**
 * Line Segment class
 * @author Ken Bäcklund
 */
public class LineSegment {
    private final double xPos;
    private final double yPos;
    private final double length;
    private final double angleRadians;

    private final double deltaX;
    private final double deltaY;



    // Constructor ////////////////////////////////////////////////////////////

    /**
     * Creates a line segment.
     * @param xPos the X position of the line segment.
     * @param yPos the Y position of the line segment.
     * @param length the length of the line segment.
     * @param angleRadians the angle in radians the line is rotated by.
     */
    public LineSegment(double xPos, double yPos, double length, double angleRadians) {
        // Correct any angle out of range.
        while (angleRadians < 0)
            angleRadians += Math.PI*2;
        this.angleRadians = angleRadians % (Math.PI*2);

        this.xPos = xPos;
        this.yPos = yPos;
        this.length = length;
        this.deltaX = Math.cos(this.angleRadians);
        this.deltaY = Math.sin(this.angleRadians);
    }

    /**
     * Get the mid X point of the line segment.
     * @return the mid X point of the line segment.
     */
    public double midX() {
        return xPos;
    }

    /**
     * Get the mid Y point of the line segment.
     * @return the mid Y point of the line segment.
     */
    public double midY() {
        return yPos;
    }

    /**
     * Get the leftmost X position of the line segment.
     * @return the leftmost X position of the line segment.
     */
    public double x1() {
        return xPos - length*deltaX/2;
    }

    /**
     * Get the leftmost Y position of the line segment.
     * @return the leftmost Y position of the line segment.
     */
    public double y1() {
        return yPos - length*deltaY/2;
    }

    /**
     * Get the rightmost X position of the line segment.
     * @return the rightmost X position of the line segment.
     */
    public double x2() {
        return xPos + length*deltaX/2;
    }

    /**
     * Get the rightmost Y position of the line segment.
     * @return the rightmost Y position of the line segment.
     */
    public double y2() {
        return yPos + length*deltaY/2;
    }

    /**
     * Get the length of the line segment.
     * @return the length of the line segment.
     */
    public double length() {
        return length;
    }

    /**
     * Get the direction in radians the line segment is rotated by.
     * @return the direction in radians the line segment is rotated by.
     */
    public double direction() {
        return angleRadians;
    }

    /**
     * Get the horizontal direction the line segment is rotated by.
     * @return the horizontal direction the line segment is rotated by.
     */
    public double deltaX() {
        return deltaX;
    }

    /**
     * Get the vertical direction the line segment is rotated by.
     * @return the vertical direction the line segment is rotated by.
     */
    public double deltaY() {
        return deltaY;
    }

    /**
     * Get the distance from a given point to nearest point on the line segment.
     * @param fromX the point's X position.
     * @param fromY the point's Y position.
     * @return the distance from a given point to nearest point on the line segment.
     */
    public double distance(double fromX, double fromY) {
        double dx = xPos - fromX;
        double dy = yPos - fromY;
        double a = angleBetweenPoints( xPos, yPos, fromX, fromY) - angleRadians;

        //Get distance from center to intersection point of the perpendicular line
        // passing through the point, without exceeding the line's length.
        double lineDist = Math.sqrt(dx*dx+dy*dy) * Math.cos(a);
        double maxLineDist = Math.min( Math.max(-length/2, lineDist), length/2);

        // Get delta position between point, and closest point on line segment.
        dx = xPos + maxLineDist * Math.cos(angleRadians) - fromX;
        dy = yPos + maxLineDist * Math.sin(angleRadians) - fromY;
        // Delta value gives the distance
        return Math.sqrt(dx*dx+dy*dy);
    }



    // Private helper methods /////////////////////////////////////////////////

    /**
     * Get the angle in radians between two points.
     * @param x1 X position of first point.
     * @param y1 Y position of first point.
     * @param x2 X position of second point.
     * @param y2 Y position of second point.
     * @return the angle in radians between two points.
     */
    private double angleBetweenPoints(double x1, double y1, double x2, double y2) {
        double angle = Math.atan2(y2-y1, x2-x1);
        return (angle < 0) ? angle + Math.PI*2 : angle;
    }


}
