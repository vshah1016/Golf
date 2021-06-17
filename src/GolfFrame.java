import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GolfFrame extends JPanel implements KeyListener, ActionListener {
    final GolfBall golfBall;
    final Shape[] obstacles;
    private boolean moving;

    Timer t = new Timer(15, this);

    public Shooter line;
    public GolfFrame(GolfBall golfBall, Shape[] obstacles, boolean moving) throws InterruptedException {
        super(new BorderLayout());
        this.golfBall = golfBall;
        this.obstacles = obstacles;
        this.moving = moving;
        t.start();
        setSize(new Dimension(1920, 1080));
        setBackground(new Color(31, 163, 71));
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
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
        Point currentLocation = golfBall.location();
        g.setColor(Color.WHITE);
        g.fillOval(currentLocation.x, currentLocation.y, 25,  25);
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
        this.repaint();
    }

    public void move(){
        moving = true;
        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            keyboardPress(e);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
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
