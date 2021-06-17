import javax.swing.*;
import java.awt.*;

public class Main {
    static JFrame jFrame = new JFrame();
    static Shape[] obstacles = CourseGen.genObstacle();
    static Locations locations = new Locations(new Point(80, 900));
    static GolfBall golfBall = new GolfBall();
    static GolfFrame golfCourse = new GolfFrame(golfBall, obstacles, false);
    public static void main(String[] args) throws InterruptedException {
        golfCourse.shoot();
        jFrame.add(golfCourse);
        jFrame.pack();
        jFrame.setSize(1920, 1080);
        jFrame.setVisible(true);
        Thread.sleep(1000);
        Point newPoint = new Point(1700, 450);
        Shot shot = new Shot(newPoint, 900);
        for (Path path : shot.paths){
            golfBall.move(path.endingPoint, path.vi, path.vf);
        }
//        golfBall.move(new Point(60, 300));
    }
}
