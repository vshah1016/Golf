import javax.swing.*;
import java.awt.*;

public class Main {
    static JFrame jFrame = new JFrame();
    public static void main(String[] args) throws InterruptedException {
        GolfBall golfBall = new GolfBall(new Point(960, 900));
        GolfFrame golfCourse = new GolfFrame(golfBall);
        jFrame.getContentPane().setBackground(new Color(31, 163, 71));
        jFrame.add(golfCourse);
        jFrame.pack();
        jFrame.setSize(1920, 1080);
        jFrame.setVisible(true);
        Thread.sleep(1000);
        golfBall.move(new Point(300, 300));
    }
}
