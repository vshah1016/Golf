import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GolfFrame extends JPanel implements ActionListener {
    final GolfBall golfBall;
    final Shape[] obstacles;
    final boolean moving;
    public GolfFrame(GolfBall golfBall, Shape[] obstacles, boolean moving) {
        this.golfBall = golfBall;
        this.obstacles = obstacles;
        this.moving = moving;
        setSize(new Dimension(1920, 1080));
        setBackground(new Color(31, 163, 71));
        setFocusable(true);
        setVisible(true);
//        addKeyListener(new TAdapter());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
