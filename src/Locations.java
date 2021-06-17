import java.util.ArrayList;

public class Locations {
    private final ArrayList<Point> locations = new ArrayList<>();
    private final Point initialPoint;

    public Locations(Point initialPoint) {
        locations.add(initialPoint);
        this.initialPoint = initialPoint;
    }

    public void newLocations(Point point){
        locations.add(point);
        if (locations.size() > 10) locations.remove(0);
    }

    public Point currentLocation(){
        return locations.get(locations.size() - 1);
    }

    public void reset(){
        locations.clear();
        locations.add(initialPoint);
    }
}
