import javax.swing.*;

public class Main {
    static int[][] golfCourse = new int[1920][1080];

    public static void main(String[] args) {
        for (int i = 0; i < golfCourse.length; i++) {
            for (int j = 0; j < golfCourse[0].length; j++) {
                golfCourse[i][j] = (int) (Math.random() * 2) + 1;
            }
        }
        GolfCourse golfCourse = new GolfCourse();
        JFrame jFrame = new JFrame();
        jFrame.setContentPane(golfCourse.frame);
        jFrame.pack();
        jFrame.setSize(500,200);
        jFrame.setVisible(true);
    }
}
