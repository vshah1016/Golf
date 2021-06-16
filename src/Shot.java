import java.util.ArrayList;

public class Shot {
    final Point destination;
    final Locations locations = Main.locations;
    final ArrayList<Path> paths = new ArrayList<>();
    final int power;

    public Shot(Point destination, int power) {
        this.destination = destination;
        this.power = power;
        calculate();
    }

    public Point startingPoint(){
        return paths.get(0).startingPoint;
    }
    public Point endingPoint(){
        if (paths.isEmpty()) return null;
        return paths.get(paths.size() - 1).endingPoint;
    }

    private Path lastPath(){
        return paths.get(paths.size() - 1);
    }

    public void calculate(){
        Point currentLocation = locations.currentLocation();
        while(paths.isEmpty() && power <= 0){
            Path newPath;
            Point startingPoint, endingPoint;
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
            double m = 0, b = 0; // y = mx + b      < ---------- these three vari
            int direction = 0; //-1 or 1            < ----------                 ables to pass to suryaa
            BouncePath bouncePath = new BouncePath(0, 0, null, 0, true);

            if (bouncePath.bounce){
                //todo if bounce is within power left
                switch (bouncePath.direction){
                    case 0 -> {

                    }
                    case 1 -> {
                        switch (direction) {
                            case -1 -> {}
                            case 1 -> {}
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected bounce value: " + bouncePath.direction);
                }
            } else {
                endingPoint = destination;
            }
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
