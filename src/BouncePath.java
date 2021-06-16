public class BouncePath {
    public final double m; // y = mx + b for NEW line
    public final double b;
    public final Point intersection;
    public final int direction; //0: Left or Right //1: Up or Down
    public final boolean bounce; // does it bounce or does it not. if no bounce, you can put null and 0 for rest

    public BouncePath(double m, double b, Point intersection, int direction, boolean bounce) {
        this.m = m;
        this.b = b;
        this.intersection = intersection;
        this.direction = direction;
        this.bounce = bounce;
    }
}