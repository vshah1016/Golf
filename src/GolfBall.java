import java.awt.*;
import java.util.ArrayList;

public class GolfBall {
    public static final int SIZE = 25;
    public static final Locations locations = Main.locations;
    private final Shape[] obstacles;

    public GolfBall(Shape[] obstacles) {
        this.obstacles = obstacles;
    }

    public void draw(Graphics g) {
        Point currentLocation = locations.currentLocation();
        g.setColor(Color.WHITE);
        g.fillOval(currentLocation.x, currentLocation.y, SIZE, SIZE);
    }

    public void move(Point newLocation, int vi, int vf) throws InterruptedException {
        Point currentLocation;
        currentLocation = locations.currentLocation();
        double m = (double)(newLocation.y - currentLocation.y) / (double)(newLocation.x - currentLocation.x);
        double b = currentLocation.y - (m * currentLocation.x);
        double d1 = currentLocation.distance(newLocation);
        int v = vi;
        while (v > vf) {
            currentLocation = locations.currentLocation();
            double d = currentLocation.distance(newLocation);
            int x3 = (int) Math.ceil((currentLocation.x - Math.ceil((v * (currentLocation.x - newLocation.x) / d))));
            int y3 = (int) Math.ceil((m * x3 + b));
            Point point3 = new Point(x3, y3);
            locations.newLocations(point3);
            Thread.sleep(10);
            Main.jFrame.repaint();
            v = (int) ((vi * (d / d1))) + vf;
            if (v == 1 || v == 0) v = 2;
            if ((int) d < 2) v = vf;
        }
    }

    private void bounceLR(int v, int CONSTANT_POWER, Point distantPoint, double slopeBounce, double intercept) throws InterruptedException {
        Point currentLocation = locations.currentLocation();
        locations.bounce();
        double d = currentLocation.distance(distantPoint);
        int x3 = (int) Math.ceil((currentLocation.x - Math.ceil((CONSTANT_POWER * (currentLocation.x - distantPoint.x) / d))));
        int y3 = (int) (x3 * slopeBounce + intercept);
        move(new Point(x3, y3), v, 100);
    }

    public void newMove(Path path){

    }

}
