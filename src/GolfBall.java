import java.awt.*;
import java.util.ArrayList;

public class GolfBall {
    public static final int SIZE = 25;
    private final Locations locations;
    private final Shape[] obstacles;

    public GolfBall(Point starting, Shape[] obstacles) {
        locations = new Locations(new Point(starting.x, starting.y));
        this.obstacles = obstacles;
    }

    public GolfBall(Locations locations, Shape[] obstacles) {
        this.locations = locations;
        this.obstacles = obstacles;
    }

    public void draw(Graphics g) {
        Point currentLocation = locations.currentLocation();
        g.setColor(Color.WHITE);
        g.fillOval(currentLocation.x, currentLocation.y, SIZE, SIZE);
    }

    public void move(Point newLocation) throws InterruptedException {
        //TODO CHECK BOUNCE WITHIN POINT
        Point currentLocation, initialPoint;
        currentLocation = locations.currentLocation();
        initialPoint = locations.currentLocation();
        //todo calc how many iteration and all transition points then animate
        double m = (double)(newLocation.y - currentLocation.y) / (double)(newLocation.x - currentLocation.x);
        double b = currentLocation.y - (m * currentLocation.x);
        ArrayList<Point> points = new ArrayList<>();
        double d1 = Math.sqrt(Math.pow(currentLocation.x - newLocation.x, 2) + Math.pow(currentLocation.y - newLocation.y, 2));
        int v = 30;
        int vi = v;
        for(int i = 0; v > 0; i++){
            double d = Math.sqrt(Math.pow(currentLocation.x - newLocation.x, 2) + Math.pow(currentLocation.y - newLocation.y, 2));
            int x3 = (int) Math.ceil((currentLocation.x - Math.ceil((v * (currentLocation.x - newLocation.x) / d))));
            int y3 = (int) Math.ceil((m * x3 + b));
            Point point3 = new Point(x3, y3);
            points.add(point3);
            currentLocation = point3;
            double d2 = Math.sqrt(Math.pow(initialPoint.x - currentLocation.x, 2) + Math.pow(initialPoint.y - currentLocation.y, 2));
            v = (int) ((vi * (d / d1)));
            if (v == 0) v = 1;
            if ((int) d < 5) v = 0;
        }
        points.add(newLocation);
        for (Point point : points) {
            locations.newLocations(point);
            GolfFrame golfFrame = new GolfFrame(new GolfBall(locations, obstacles), obstacles);
            Main.jFrame.add(golfFrame);
            Thread.sleep(10);
            Main.jFrame.validate();
            Main.jFrame.repaint();
        }
    }

}
