import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Bounce {
    public static BouncePath bounce(double m, double b, int direction, int ballCurrX, int ballCurrY){
        Shape[] obstacles = Main.obstacles;
        ArrayList<Integer> arrX = new ArrayList<>();
        for (int i = 1; i < obstacles.length; i++) {
            Shape shape = obstacles[i];
            double[][] coors = shape.coors;
            double topX = ((coors[1][1] - b) / m);
            double botX = ((coors[0][1] - b) / m);
            double leftY = m * coors[0][0] + b;
            double rightY = m * coors[1][0] + b;
            if (topX >= coors[0][0] && topX <= coors[1][0]) arrX.add((int) topX);
            if (botX >= coors[0][0] && botX <= coors[1][0]) arrX.add((int) botX);
            if (leftY >= coors[0][1] && leftY <= coors[1][1]) arrX.add((int) coors[0][0]);
            if (rightY >= coors[0][1] && rightY <= coors[1][1]) arrX.add((int) coors[1][0]);
        }
        Collections.sort(arrX);
        if(arrX.size()>0) if (direction == 1) {
            Point point = new Point(arrX.get(0), (int) (m * (arrX.get(0)) + b));
            return new BouncePath(-1.0 * m, point.y - m * point.x, point, 0, true);
        } else {
            Point point = new Point(arrX.get(arrX.size() - 1), (int) (m * (arrX.get(arrX.size() - 1) + b)));
            return new BouncePath(-1.0 * m, point.y - m * point.x, point, 1, true);
        }
        return new BouncePath(0,0,new Point(0,0),0,false);
    }
}
