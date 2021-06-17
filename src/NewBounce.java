import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NewBounce {
    public static BouncePath bounce(double m, double b, int direction, int ballCurrX, int ballCurrY){
        Shape[] obstacles = Main.obstacles;
        ArrayList<Integer> arrX = new ArrayList<>();
        ArrayList<Integer> arrXside = new ArrayList<>();
        for (int i = 1; i < obstacles.length; i++) {
            Shape shape = obstacles[i];
            double[][] coors = shape.coors;
            double topX = ((coors[1][1] - b) / m);
            double botX = ((coors[0][1] - b) / m);
            double leftY = m * coors[0][0] + b;
            double rightY = m * coors[1][0] + b;
            if (topX >= coors[0][0] && topX <= coors[1][0]) arrX.add((int) topX);
            if (botX >= coors[0][0] && botX <= coors[1][0]) arrX.add((int) botX);
            if (leftY >= coors[0][1] && leftY <= coors[1][1]) arrXside.add((int) coors[0][0]);
            if (rightY >= coors[0][1] && rightY <= coors[1][1]) arrXside.add((int) coors[1][0]);
        }
        Collections.sort(arrX);
        Collections.sort(arrXside);
        boolean side = false;
        int min =0;
        if(arrX.size()>0||arrXside.size()>0)
            if (direction == 1) {
            if(!arrXside.isEmpty()&&!arrX.isEmpty()&&arrXside.get(0)<arrX.get(0)){side=true; min=arrXside.get(0);}
            else if(arrX.isEmpty()){side=true; min=arrXside.get(0);}
            else{min=arrX.get(0);}

            Point point = new Point(min, (int) (m * (min) + b));
            if(side){return new BouncePath(-1.0 * m, point.y + m * point.x, point, 0, true);}
            System.out.println("g");
            return new BouncePath(-1.0 * m, point.y - m * point.x, point, 1, true);
        } else {
            if(!arrXside.isEmpty()&&!arrX.isEmpty()&&arrXside.get(arrXside.size()-1)>arrX.get(arrX.size()-1)){side=true; min=arrXside.get(arrXside.size()-1);}
            else if(arrX.isEmpty()){side=true; min=arrXside.get(arrXside.size()-1);}
            else{ min=arrX.get(arrX.size()-1);}

            Point point = new Point(min, (int) (m * min + b));
            if(side){return new BouncePath(-1.0 * m, point.y - m * point.x, point, 1, true);}
            return new BouncePath(-1.0*m, point.y - m * point.x, point, 0, true);
        }
        return new BouncePath(0,0,new Point(0,0),0,false);
    }
}
