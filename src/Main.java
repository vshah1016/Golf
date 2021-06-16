import javax.swing.*;
import java.awt.*;

public class Main {
    static JFrame jFrame = new JFrame();
    static Shape[] obstacles = CourseGen.genObstacle();
    static Locations locations = new Locations(new Point(80, 900));
    public static void main(String[] args) throws InterruptedException {
        GolfBall golfBall = new GolfBall(obstacles);
        GolfFrame golfCourse = new GolfFrame(golfBall, obstacles);
        jFrame.getContentPane().setBackground(new Color(31, 163, 71));
        jFrame.add(golfCourse);
        jFrame.pack();
        jFrame.setSize(1920, 1080);
        jFrame.setVisible(true);
        Thread.sleep(1000);
        int power = 100;
        golfBall.move(new Point(1100, 100), 30, power);
//        golfBall.move(new Point(60, 300));
    }
}
