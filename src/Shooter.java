public class Shooter {
    public int power = 100;

    Point starting;
    int angle = 0;

    public Shooter(Point starting) {
        this.starting = starting;
    }

    public void increasePower() {
        if (power < 100) power++;
    }

    public void decreasePower() {
        if (power > 1) power--;
    }

    public void angleLeft() {
        angle--;
        if (angle == -1) angle = 359;
    }

    public void angleRight() {
        angle++;
        if (angle == 360) angle = 0;
    }

    public Point endPoint() {
        int x = (int) (starting.x + power * Math.cos(Math.toRadians(angle)));
        int y = (int) (starting.y + power * Math.sin(Math.toRadians(angle)));
        return new Point(x, y);
    }

}
