import java.awt.*;
import java.util.ArrayList;

public class GolfBall {
    public static final int SIZE = 25;
    private final Locations locations;

    public GolfBall(Point starting) {
        locations = new Locations(new Point(starting.x, starting.y));
    }

    public GolfBall(Locations locations) {
        this.locations = locations;
    }

    public void draw(Graphics g) {
        Point currentLocation = locations.currentLocation();
        g.setColor(Color.WHITE);
        g.fillOval(currentLocation.x, currentLocation.y, SIZE, SIZE);
    }

    public void move(Point newLocation) throws InterruptedException {
        //TODO CHECK BOUNCE WITHIN POINT
        Point currentLocation, midPoint;
        currentLocation = locations.currentLocation();
        //todo calc how many iteration and all transition points then animate
        double m = (double)(newLocation.y - currentLocation.y) / (double)(newLocation.x - currentLocation.x);
        double b = currentLocation.y - (m * currentLocation.x);
        ArrayList<Point> points = new ArrayList<>();
        double d = Math.sqrt(Math.pow(currentLocation.x - newLocation.x, 2) + Math.pow(currentLocation.y - newLocation.y, 2));
        int v = 10;
        for(int i = 0; v > 0; i++){
            d = Math.sqrt(Math.pow(currentLocation.x - newLocation.x, 2) + Math.pow(currentLocation.y - newLocation.y, 2));
            int x3 = (int) Math.ceil((int) (currentLocation.x - ((v * (currentLocation.x - newLocation.x)) / d)));
            int y3 = (int) Math.ceil((int) (m * x3 + b));
            Point point3 = new Point(x3, y3);
            points.add(point3);
            currentLocation = point3;
            if (i % 15 == 0) v--;
            if (v == 0) v = 1;
            if ((int) d < 5) v = 0;
        }
        points.add(newLocation);
        for (Point point : points) {
            locations.newLocations(point);
            GolfFrame golfFrame = new GolfFrame(new GolfBall(locations));
            Main.jFrame.add(golfFrame);
            Thread.sleep(10);
            Main.jFrame.validate();
            Main.jFrame.repaint();
        }
    }

}
