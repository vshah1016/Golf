import javax.swing.*;

public class Main {
    static int[][] golfData = new int[1920][1080];

    public static void main(String[] args) {
        GolfCourse golfCourse = new GolfCourse();
        JFrame jFrame = new JFrame();
        jFrame.add(golfCourse.frame);
        jFrame.pack();
        jFrame.setSize(1920, 1080);
        jFrame.setVisible(true);

        JButton mo_lord = golfCourse.getMo_lord();

    }
}
