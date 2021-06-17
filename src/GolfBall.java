import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GolfBall {
    public static final int SIZE = 25;
    public static final Locations locations = Main.locations;

    public static boolean stop = false;

    public Point location(){
        return locations.currentLocation();
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
            int x3 = (int) Math.ceil((currentLocation.x - Math.ceil((v * (currentLocation.x - newLocation.x)) / d)));
            int y3 = (int) Math.ceil((m * x3 + b));
            Point point3 = new Point(x3, y3);
            locations.newLocations(point3);
//            Thread.sleep();
            TimeUnit.MILLISECONDS.sleep(10);
            Main.golfCourse.repaint();
            int newv = (int) (vi * (d / d1)) + vf;
            v = Math.min(v, newv);
            if (v == 1 || v == 0) v = 2;
            if ((int) d < 2) v = vf;
            if (stop){
                v = vf;
                stop = false;
            }
            Point hole = new Point((int) Main.obstacles[0].center[0], (int) Main.obstacles[0].center[1]);
            if (locations.currentLocation().distance(hole) < 20) Main.won();
        }
    }
}
