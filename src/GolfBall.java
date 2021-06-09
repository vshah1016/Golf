import java.awt.*;

public class GolfBall {
    private Locations locations;
    private final int SIZE = 25;

    public GolfBall(int x, int y) {
        locations = new Locations(new Point(x, y));
    }

    private void draw(Graphics g){
        Point currentLocation = locations.currentLocation();
        g.fillOval(currentLocation.x, currentLocation.y, SIZE, SIZE);
    }

    public void move(int x, int y, Graphics g){
        Point currentLocation, newLocation;
        currentLocation = locations.currentLocation();
        newLocation = new Point(x, y);

        locations.newLocations(newLocation);
        reDraw(g);
        //TODO: move ball
    }

    private void reDraw(Graphics g){
        //TODO: redraw the ball
    }


}
