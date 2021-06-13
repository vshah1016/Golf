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
//        g.drawPolygon();
/*
        for (int i = 0; i < Main.golfData.length; i++) {
            for (int j = 0; j < Main.golfData[0].length; j++) {
                Main.golfData[i][j] = (int) (Math.random() * 3) + 1;
//                if (i > 700 && i < 750 && j > 700 && j < 750) Main.golfData[i][j] = 2;
                switch (Main.golfData[i][j]) {
                    case 1 -> {
                        g.setColor(Color.GREEN);
                        g.drawLine(i, j, i, j);
                    }
                    case 2 -> {
                        g.setColor(Color.BLACK);
                        g.drawLine(i, j, i, j);
                    }
                    case 3 -> {
                        g.setColor(Color.red);
                        g.drawLine(i, j, i, j);
                    }
                }
            }
        }
*/
    }
}
