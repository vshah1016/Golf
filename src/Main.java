import javax.swing.*;
import java.awt.*;

public class Main {
    static JFrame jFrame = new JFrame();
    public static void main(String[] args) throws InterruptedException {
        Shape[] obstacles = CourseGen.genObstacle();
        GolfBall golfBall = new GolfBall(new Point(960, 900), obstacles);
        GolfFrame golfCourse = new GolfFrame(golfBall, obstacles);
        jFrame.getContentPane().setBackground(new Color(31, 163, 71));
        jFrame.add(golfCourse);
        jFrame.pack();
        jFrame.setSize(1920, 1080);
        jFrame.setVisible(true);
        Thread.sleep(1000);
        golfBall.move(new Point(300, 300));
    }
}
