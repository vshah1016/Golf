import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static JFrame jFrame = new JFrame();
    static Shape[] obstacles = CourseGen.genObstacle();
    static Locations locations = new Locations(new Point(80, 900));
    static GolfBall golfBall = new GolfBall();
    static GolfFrame golfCourse;

    static {
        try {
            golfCourse = new GolfFrame(golfBall, obstacles, false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
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

        while (true) {
            System.out.print("Shoot: ");
            scanner.next();
            shootBall();
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
}
