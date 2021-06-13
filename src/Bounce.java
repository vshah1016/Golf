public class Bounce {
    public double[] isTouching(Shape currCircle, Shape[] Obstacles){
        int radius =25;
        double[] center = currCircle.center;
        for(Shape obs: Obstacles){
            double[][] coors = obs.coors;
            for (int i = (int) coors[0][1]; i < coors[1][1]; i++) {
                if (Math.pow((coors[0][0] - center[0]), 2) + Math.pow((i - center[1]), 2) == Math.pow(radius, 2)) {
                    return new double[]{coors[0][0],coors[0][1],coors[0][0],coors[1][1]};
                }
            }
            for (int i = (int) coors[0][1]; i < coors[1][1]; i++) {
                if (Math.pow((coors[1][0] - center[0]), 2) + Math.pow((i - center[1]), 2) == Math.pow(radius, 2)) {
                    return new double[]{coors[1][0],coors[0][1],coors[1][0],coors[1][1]};
                }
            }
            for (int i = (int) coors[1][0]; i < coors[0][0]; i++) {
                if (Math.pow((i - center[0]), 2) + Math.pow(coors[0][1], 2) == Math.pow(radius, 2)) {
                    return new double[]{coors[0][0],coors[1][1],coors[1][0],coors[1][1]};
                }
            }
            for (int i = (int) coors[1][0]; i < coors[0][0]; i++) {
                if (Math.pow((i - center[0]), 2) + Math.pow(coors[1][1], 2) == Math.pow(radius, 2)) {
                    return new double[]{coors[0][0],coors[0][0],coors[1][0],coors[0][0]};
                }
            }
        }
        if((center[0]+25>1920)){return new double[]{1920,1080,1920,0};}
        if((center[0]-25<0)){return new double[]{0,0,0,1080};}
        if((center[1]+25>1080)){return new double[]{0,1080,1920,1080};}
        if((center[1]-25<0)){return new double[]{0,0,1920,0};}

        return new double[]{-1,-1,-1,-1};
    }
}
//return as x1,y1,x2,y2 @Wal