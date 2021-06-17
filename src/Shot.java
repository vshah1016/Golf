import java.util.ArrayList;

public class Shot {
    final Point destination;
    final Locations locations = Main.locations;
    final ArrayList<Path> paths = new ArrayList<>();
    int power;
    final int poweri;

    public Shot(Point destination, int power, double angle) {
        this.destination = destination;
        this.power = power;
        this.poweri = power;

        calculate(0, 0,1);
    }

    public Point startingPoint(){
        return paths.get(0).startingPoint;
    }
    public Point endingPoint(){
        if (paths.isEmpty()) return locations.currentLocation();
        return paths.get(paths.size() - 1).endingPoint;
    }
    public int endingVelocity(){
        if (paths.isEmpty()) return 30;
        return paths.get(paths.size() - 1).vf;
    }

    private Path lastPath(){
        return paths.get(paths.size() - 1);
    }

    public void calculate(double m, double b, int direction){
        Point currentLocation = locations.currentLocation();
        while(paths.isEmpty() && power <= 0){
            Path newPath;
            Point startingPoint, endingPoint = null;
            int vi, vf;

            //set initial velocity and starting point of path that is being made
            if(paths.isEmpty()){
                vi = 30;
                startingPoint = currentLocation;
            } else {
                vi = lastPath().vi;
                startingPoint = lastPath().endingPoint;
            }

            //todo replace "new BouncePath..." with @suryaa method and assign variables to actual values
            BouncePath bouncePath = NewBounce.bounce(m, b, direction, endingPoint().x, endingPoint().y);

            double intersectToEnd = bouncePath.intersection.distance(endingPoint());
            if (bouncePath.bounce && intersectToEnd <= power){
                endingPoint = bouncePath.intersection;
                power -= intersectToEnd;
                vf = (int) (30.0 * (power / poweri) + 0.5);
            } else {
                switch (bouncePath.direction){
                    case 0 -> {
                        Point relativeDestination = new Point(0, (int) bouncePath.b);
                        int x3 = (int) Math.ceil((startingPoint.x - Math.ceil((power * (startingPoint.x - relativeDestination.x) / startingPoint.distance(relativeDestination)))));
                        int y3 = (int) (x3 * bouncePath.m + bouncePath.b);
                        endingPoint = new Point(x3, y3);
                    }
                    case 1 -> {
                        Point relativeDestination = new Point(1920, (int) (1920 * bouncePath.m + bouncePath.b));
                        int x3 = (int) Math.ceil((startingPoint.x - Math.ceil((power * (startingPoint.x - relativeDestination.x) / startingPoint.distance(relativeDestination)))));
                        int y3 = (int) (x3 * bouncePath.m + bouncePath.b);
                        endingPoint = new Point(x3, y3);
                    }
                }
                vf = 0;
            }
            m = bouncePath.m;
            b = bouncePath.b;
            direction = bouncePath.direction;

            newPath = new Path(startingPoint, endingPoint, vi, vf);
            paths.add(newPath);
        }
    }
}

class Path {
   final Point startingPoint;
   final Point endingPoint;
   final int vi;
   final int vf;


    Path(Point startingPoint, Point endingPoint, int vi, int vf) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.vi = vi;
        this.vf = vf;
    }
}
