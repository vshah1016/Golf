import java.util.ArrayList;

public class Locations {
    private final ArrayList<Point> locations = new ArrayList<>();

    public Locations(Point initialPoint) {
        locations.add(initialPoint);
    }

    public void newLocations(Point point){
        locations.add(point);
        if (locations.size() > 10) locations.remove(0);
    }

    public Point currentLocation(){
        return locations.get(locations.size() - 1);
    }

    public double getVelocity() {
        //TODO: @SURYAAcoolness@gmail.com
        return 0;
    }

}