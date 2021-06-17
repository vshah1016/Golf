import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NewBounce {
    int deltaY;
    int deltaX;
    double slope;
    int b;
    int direction;
    int ballCurrX;
    int ballCurrY;
    public BouncePath bounce(Shape[] obstacles){

        ArrayList<Integer> arrX = new ArrayList<>();

        for(Shape shape: obstacles){
            double [][] coors= shape.coors;
            double topX = ((coors[1][1]-b)/slope);
            double botX = ((coors[0][1]-b)/slope);
            double leftY = slope*coors[0][0]+b;
            double rightY = slope*coors[1][0]+b;
            if(topX>=coors[0][0]&&topX<=coors[1][0]){arrX.add((int)topX);}
            if(botX>=coors[0][0]&&botX<=coors[1][0]){arrX.add((int)botX);}
            if(leftY>=coors[0][1]&&leftY<=coors[1][1]){arrX.add((int)coors[0][0]);}
            if(rightY>=coors[0][1]&&rightY<=coors[1][1]){arrX.add((int)coors[1][0]);}
        }

        Collections.sort(arrX);
        if(arrX.size()>0){
            if (direction == 1) {
                Point point = new Point((int) arrX.get(0), (int) (slope * (arrX.get(0)) + b));
                return new BouncePath(-1.0*(1.0*deltaY/deltaX),point.y-(1.0*deltaY/deltaX)* point.x,point,0,true);
            } else {
                Point point = new Point((int) arrX.get(arrX.size()-1), (int) ((slope * 1.0) * (arrX.get(arrX.size()-1) + b)));
                return new BouncePath(-1.0*(1.0*deltaY/deltaX),point.y-(1.0*deltaY/deltaX)* point.x,point,1,true);

            }
        }
        return new BouncePath(0,0,new Point(0,0),0,false);
    }
}
