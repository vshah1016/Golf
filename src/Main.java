import javax.swing.*;

public class Main {
    static int[][] golfData = new int[1920][1080];

    public static void main(String[] args) {
        GoatFrame golfCourse = new GoatFrame();
        JFrame jFrame = new JFrame();
        jFrame.add(golfCourse);
        jFrame.pack();
        jFrame.setSize(1920, 1080);
        jFrame.setVisible(true);


    }
}
