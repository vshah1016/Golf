public class BallCenter extends Point{
    private static final int OFFSET = GolfBall.SIZE;

    public BallCenter(int x, int y) {
        super(x + OFFSET, y - OFFSET);
    }

    public BallCenter(Point point) {
        super(point.x + OFFSET, point.y - OFFSET);
    }

    public Point realPoint(){
        return new Point(x - OFFSET, y + OFFSET);
    }
}
