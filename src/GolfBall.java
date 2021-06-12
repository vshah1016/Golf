import java.awt.*;

public class GolfBall {
    private final Locations locations;
    public static final int SIZE = 25;

    public GolfBall(Point starting) {
        locations = new Locations(new Point(starting.x, starting.y));
    }

    public GolfBall(Locations locations){
        this.locations = locations;
    }

    public void draw(Graphics g){
        Point currentLocation = locations.currentLocation();
        g.setColor(Color.BLACK);
        g.fillOval(currentLocation.x, currentLocation.y, SIZE, SIZE);
    }

    public void move(Point newLocation){
        //TODO CHECK BOUNCE WITHIN POINT
        Point currentLocation, midPoint;
        currentLocation = locations.currentLocation();
        //todo calc hoiw many iteration and all transition points then animate
        locations.newLocations(newLocation);
        GolfFrame golfFrame = new GolfFrame(new GolfBall(locations));
        Main.jFrame.add(golfFrame);
        Main.jFrame.validate();
        Main.jFrame.repaint();
    }

}
