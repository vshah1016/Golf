import javax.swing.*;
import java.awt.*;

public class Main {
    static JFrame jFrame = new JFrame();
    static Shape[] obstacles = CourseGen.genObstacle();
    static Locations locations = new Locations(new Point(80, 900));
    public static void main(String[] args) throws InterruptedException {
        GolfBall golfBall = new GolfBall(obstacles);
        GolfFrame golfCourse = new GolfFrame(golfBall, obstacles, false);
        jFrame.add(golfCourse);
        jFrame.pack();
        jFrame.setSize(1920, 1080);
        jFrame.setVisible(true);
        Thread.sleep(1000);
        int power = 100;
        Shot shot = new Shot(new Point(1100, 100), power, 0);
        for (Path path : shot.paths){
            golfBall.move(path.endingPoint, path.vi, path.vf);
        }
//        golfBall.move(new Point(60, 300));
    }
}
