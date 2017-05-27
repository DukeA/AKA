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

    public LineSegment(double xPos, double yPos, double length, double angleRadians) {
        while (angleRadians < 0)
            angleRadians += Math.PI*2;
        this.angleRadians = angleRadians % (Math.PI*2);

        this.xPos = xPos;
        this.yPos = yPos;
        this.length = length;
        this.deltaX = Math.cos(this.angleRadians);
        this.deltaY = Math.sin(this.angleRadians);
    }

    public double midX() {
        return xPos;
    }

    public double midY() {
        return yPos;
    }

    public double x1() {
        return xPos - length*deltaX/2;
    }

    public double y1() {
        return yPos - length*deltaY/2;
    }

    public double x2() {
        return xPos + length*deltaX/2;
    }

    public double y2() {
        return yPos + length*deltaY/2;
    }

    public double length() {
        return length;
    }

    public double direction() {
        return angleRadians;
    }

    public double deltaX() {
        return deltaX;
    }

    public double deltaY() {
        return deltaY;
    }

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

    private double angleBetweenPoints(double x1, double y1, double x2, double y2) {
        double angle = Math.atan2(y2-y1, x2-x1);
        return (angle < 0) ? angle + Math.PI*2 : angle;
    }


}
