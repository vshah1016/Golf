import java.awt.*;

public class GolfBall {
    private final Locations locations;
    private final int SIZE = 25;

    public GolfBall(int x, int y) {
        locations = new Locations(new Point(x, y));
    }

    private void draw(Graphics g){
        Point currentLocation = locations.currentLocation();
        g.fillOval(currentLocation.x, currentLocation.y, SIZE, SIZE);
    }

    public void move(int x, int y, Graphics g){
        Point currentLocation, newLocation, midPoint;
        currentLocation = locations.currentLocation();
        newLocation = new Point(x, y);
        midPoint = Point.midPoint(currentLocation, newLocation);
        locations.newLocations(newLocation);
        reDraw(g, midPoint);
        reDraw(g, newLocation);
    }

    private void reDraw(Graphics g, Point point){
        //TODO: redraw the ball
    }

    public Point centerPoint(Point point){
        return new Point(point.x + (SIZE / 2), point.y -  (SIZE / 2));
    }
    public Point centerPoint(){
        Point point = locations.currentLocation();
        return centerPoint(point);
    }


}
