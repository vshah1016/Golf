import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static JFrame jFrame;
    static Shape[] obstacles;
    static Locations locations = new Locations(new Point(80, 900));
    static GolfBall golfBall;
    static GolfFrame golfCourse;

    static boolean won = false;

    public static void main(String[] args) throws InterruptedException {
            jFrame = new JFrame();
            obstacles = CourseGen.genObstacle();
            golfBall = new GolfBall();
            locations.reset();
            golfCourse = new GolfFrame(golfBall, obstacles, false);

            golfCourse.shoot();
            jFrame.add(golfCourse);
            jFrame.pack();
            jFrame.setSize(1920, 1080);
            jFrame.setVisible(true);

            golfCourse.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    try {
                        golfCourse.keyboardPress(e);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            });
            int count = 1;
            while (!won) {
                System.out.print("Shot " + count + ": ");
                scanner.next();
                shootBall();
                count++;
            }
    }

    public static void shootBall() throws InterruptedException {

        Point currentLocation = Main.locations.currentLocation();
        Point endpoint = golfCourse.line.endPoint();
        double m = (endpoint.y * 1.0 - currentLocation.y) / (endpoint.x - currentLocation.x);
        double b = endpoint.y - m * endpoint.x;
        int x = currentLocation.x + (endpoint.x - currentLocation.x) * 5;
        Point newPoint = new Point(x, (int) (m * x + b));
        Shot shot = new Shot(newPoint, golfCourse.line.power * 5);
        golfCourse.move();
        for (Path path : shot.paths) {
            Main.golfBall.move(path.endingPoint, path.vi, path.vf);
        }
        Main.locations.newLocations(shot.endingPoint());
        golfCourse.shoot();
    }

    public static void won() {
        won = true;
        locations.reset();
        System.exit(0);
    }
}
