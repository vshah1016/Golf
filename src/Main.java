import javax.swing.*;
import java.awt.*;

public class Main {
    static JFrame jFrame = new JFrame();
    static Shape[] obstacles = CourseGen.genObstacle();
    static Locations locations = new Locations(new Point(80, 900));
    public static void main(String[] args) throws InterruptedException {
        GolfBall golfBall = new GolfBall();
        GolfFrame golfCourse = new GolfFrame(golfBall, obstacles, false);
        golfCourse.shoot();
        jFrame.add(golfCourse);
        jFrame.pack();
        jFrame.setSize(1920, 1080);
        jFrame.setVisible(true);
        Thread.sleep(1000);
//        golfBall.move(new Point(60, 300));
    }
}
