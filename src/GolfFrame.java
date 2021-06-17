import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GolfFrame extends JPanel implements KeyListener, ActionListener {
    final GolfBall golfBall;
    final Shape[] obstacles;
    private boolean moving;

    private Shooter line;
    public GolfFrame(GolfBall golfBall, Shape[] obstacles, boolean moving) {
        this.golfBall = golfBall;
        this.obstacles = obstacles;
        this.moving = moving;
        setSize(new Dimension(1920, 1080));
        setBackground(new Color(31, 163, 71));
        setFocusable(true);
        setVisible(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    keyboardPress(e);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!moving){
            g.setColor(Color.orange);
            Point currentLocation = Main.locations.currentLocation();
            if(line == null) line = new Shooter(currentLocation);
            Point lineEndpoint = line.endPoint();
            g.drawLine(currentLocation.x + 13, currentLocation.y + 13, lineEndpoint.x + 13, lineEndpoint.y + 13);
        }
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

    public void shoot(){
        moving = false;
        line = new Shooter(Main.locations.currentLocation());
    }

    public void move(){
        moving = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    public void keyboardPress(KeyEvent e) throws InterruptedException {
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_LEFT -> {
                line.angleLeft();
                repaint();
            }
            case KeyEvent.VK_RIGHT -> {
                line.angleRight();
                repaint();
            }
            case KeyEvent.VK_UP -> {
                line.increasePower();
                repaint();
            }
            case KeyEvent.VK_DOWN -> {
                line.decreasePower();
                repaint();
            }
            case KeyEvent.VK_ENTER -> {
                Point currentLocation = Main.locations.currentLocation();
                Point endpoint = line.endPoint();
                double m = (endpoint.y * 1.0 - currentLocation.y) / (endpoint.x - currentLocation.x);
                double b = endpoint.y - m * endpoint.x;
                int x = currentLocation.x + (endpoint.x - currentLocation.x) * 5;
                Point newPoint = new Point(x, (int) (m * x + b));
                Shot shot = new Shot(newPoint, line.power * 5);
                this.move();
                repaint();
                for (Path path : shot.paths){
                    golfBall.move(path.endingPoint, path.vi, path.vf);
                }
                Main.locations.newLocations(shot.endingPoint());
                this.shoot();
                repaint();
            }
            default -> {}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
