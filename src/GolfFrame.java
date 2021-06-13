import java.awt.*;

public class GolfFrame extends Component {
    final GolfBall golfBall;
    final Shape[] obstacles;
    public GolfFrame(GolfBall golfBall, Shape[] obstacles) {
        this.golfBall = golfBall;
        this.obstacles = obstacles;
        setSize(new Dimension(1920, 1080));
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        golfBall.draw(g);
        Shape hole = obstacles[0];
        g.setColor(Color.black);
        g.fillOval((int)hole.center[0], (int)hole.center[1], (int)hole.radius * 2, (int)hole.radius * 2);
        g.setColor(Color.blue);
        for (int i = 1; i < obstacles.length; i++) {
            int[] xCoords = {(int) obstacles[i].coors[0][0], (int) obstacles[i].coors[0][0], (int) obstacles[i].coors[1][0], (int) obstacles[i].coors[1][0]};
            int[] yCoords = {(int) obstacles[i].coors[0][1], (int) obstacles[i].coors[1][1], (int) obstacles[i].coors[1][1], (int) obstacles[i].coors[0][1]};
            g.fillPolygon(xCoords, yCoords, 4);
        }
//        g.drawPolygon();
    }
}
