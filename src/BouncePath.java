public class BouncePath {
    public final double m;
    public final double b;
    public final Point intersection;
    public final int direction; //0: L, 1:R, 2: U, 3:D
    public final boolean bounce;

    public BouncePath(double m, double b, Point intersection, int direction, boolean bounce) {
        this.m = m;
        this.b = b;
        this.intersection = intersection;
        this.direction = direction;
        this.bounce = bounce;
    }
}