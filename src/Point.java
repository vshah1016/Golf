public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point a){
        try {
            return Math.sqrt(Math.pow(this.x - a.x, 2) + Math.pow(this.y - a.y, 2));
        } catch (NullPointerException e){
            return -1;
        }
    }
}
