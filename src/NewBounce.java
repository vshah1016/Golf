import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NewBounce {
    int deltaY;
    int deltaX;
    int b;
    int direction;
    int ballCurrX;
    int ballCurrY;
    public BouncePath bounce(Shape[] obstacles){

        int j=ballCurrY;
        ArrayList<Integer> arrX = new ArrayList<>();
        for(int i=ballCurrX; i<=1920; i+=deltaX){
            for(Shape shape: obstacles){
                double [][] coors = shape.coors;
                if(coors[1][1]==j&&i>=coors[0][0]&&i<=coors[1][0]){
                    arrX.add(i);
                }
                else if(coors[0][1]==j&&i>=coors[0][0]&&i<=coors[1][0]){
                    arrX.add(i);
                }
                else if(coors[0][0]==i&&j>=coors[0][1]&&j<=coors[0][1]){
                    arrX.add(i);
                }
                else if(coors[0][1]==i&&j>=coors[0][1]&&j<=coors[0][1]){
                    arrX.add(i);
                }
            }
            j+=deltaY;
        }
        Collections.sort(arrX);
        if(arrX.size()>0){
            if (direction == 1) {
                Point point = new Point((int) arrX.get(0), (int) ((deltaY / deltaX * 1.0) * (arrX.get(0)) + b));
                return new BouncePath(-1.0*(1.0*deltaY/deltaX),point.y-(1.0*deltaY/deltaX)* point.x,point,-1,true);
            } else {
                Point point = new Point((int) arrX.get(arrX.size()-1), (int) ((deltaY / deltaX * 1.0) * (arrX.get(arrX.size())-1) + b));
                return new BouncePath(-1.0*(1.0*deltaY/deltaX),point.y-(1.0*deltaY/deltaX)* point.x,point,1,true);

            }
        }
        return new BouncePath(0,0,new Point(0,0),0,false);
    }
}
