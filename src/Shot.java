import java.util.ArrayList;

public class Shot {
    final Point destination;
    final Locations locations = Main.locations;
    final ArrayList<Path> paths = new ArrayList<>();

    public Shot(Point destination) {
        this.destination = destination;
        calculate();
    }

    public Point startingPoint(){
        return paths.get(0).startingPoint;
    }
    public Point endingPoint(){
        return paths.get(paths.size() - 1).endingPoint;
    }

    public void calculate(){
        Point currentLocation = locations.currentLocation();

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
