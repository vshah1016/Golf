import java.awt.*;
import java.util.ArrayList;

public class GolfBall {
    public static final int SIZE = 25;
    public static final Locations locations = new Locations(new Point(80, 900));
    private final Shape[] obstacles;

    public GolfBall(Shape[] obstacles) {
        this.obstacles = obstacles;
    }

    public void draw(Graphics g) {
        Point currentLocation = locations.currentLocation();
        g.setColor(Color.WHITE);
        g.fillOval(currentLocation.x, currentLocation.y, SIZE, SIZE);
    }

    public void move(Point newLocation, int speed, int power) throws InterruptedException {
        //TODO BOUNCING STARTING V ENDING V
        //TODO IMPLEMENT POWER
        Point currentLocation, initialPoint;
        currentLocation = locations.currentLocation();
        initialPoint = locations.currentLocation();
        double m = (double)(newLocation.y - currentLocation.y) / (double)(newLocation.x - currentLocation.x);
        double b = currentLocation.y - (m * currentLocation.x);
//        ArrayList<Point> points = new ArrayList<>();
        double d1 = currentLocation.distance(newLocation);
        int v = speed;
        int vi = v;
        while (v > 0) {
            currentLocation = locations.currentLocation();
            int[] bounceLine = Bounce.bounceCheck(currentLocation.y, currentLocation.x);
            boolean bounce = bounceLine[0] != -1;
            if (bounce){ // 0 lR; 1TB
                System.out.println("MAMA");
                int CONSTANT_POWER = 200; //todo implement power
                Point distantPoint;
                double slopeBounce = (double) bounceLine[1] / bounceLine[0];
                double intercept = bounceLine[3];
                switch (bounceLine[3]) {
                    case 0 -> {
                        distantPoint = new Point(1920, (int) (1920 * slopeBounce + intercept));
                        bounceLR(v, CONSTANT_POWER, distantPoint, slopeBounce, intercept);
                        return;
                    }
                    case 1 -> {
                        distantPoint = new Point(0, (int) (0 * slopeBounce + intercept));
                        bounceLR(v, CONSTANT_POWER, distantPoint, slopeBounce, intercept);
                        return;
                    }
                    case 2 -> {
                        break;
//                        distantPoint = new Point(1920, (int) (1920 * slopeBounce + intercept));
//                        double d = currentLocation.distance(distantPoint);
//                        int x3 = (int) Math.ceil((currentLocation.x - Math.ceil((CONSTANT_POWER * (currentLocation.x - distantPoint.x) / d))));
//                        int y3 = (int) (x3 * slopeBounce + intercept);
//                        move(new Point(x3, y3), v, 100);
                    }
                    case 3 -> {
                        break;
//                        distantPoint = new Point(1920, (int) (1920 * slopeBounce + intercept));
//                        double d = currentLocation.distance(distantPoint);
//                        int x3 = (int) Math.ceil((currentLocation.x - Math.ceil((CONSTANT_POWER * (currentLocation.x - distantPoint.x) / d))));
//                        int y3 = (int) (x3 * slopeBounce + intercept);
//                        move(new Point(x3, y3), v, 100);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + bounceLine[4]);
                }
            }
            double d = currentLocation.distance(newLocation);
            int x3 = (int) Math.ceil((currentLocation.x - Math.ceil((v * (currentLocation.x - newLocation.x) / d))));
            int y3 = (int) Math.ceil((m * x3 + b));
            Point point3 = new Point(x3, y3);
            locations.newLocations(point3);
            GolfFrame golfFrame = new GolfFrame(new GolfBall(obstacles), obstacles);
            Main.jFrame.add(golfFrame);
            Thread.sleep(10);
            Main.jFrame.validate();
            Main.jFrame.repaint();
            double d2 = initialPoint.distance(currentLocation);
            v = (int) ((vi * (d / d1)));
            if (v == 1 || v == 0) v = 2;
            if ((int) d < 2) v = 0;
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

}
