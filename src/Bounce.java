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
    public static int[] bounceCheck ( int vert, int hor) //This is the important method here
    {
        for (int i = 0; i < obstacles.size(); i++) {
            int[][] points = hitting();
            if (points[0][0] != -1) {
                int changeY = (points[1][1] - points[0][1]);
                int changeX = (points[1][0] - points[0][0]);
//Slope of final trajectory = wallSlope/(initialDirection/wallSlope);

                int diffY = changeX * vert;
                int diffX = changeY * hor;

                int newY = diffX * y;
                int newX = diffY * x;
                int[] arr = new int[2];
                arr[0] = newX;
                arr[1] = newY;
                return arr;

            }
        }

    }
}
//return as x1,y1,x2,y2 @Wal
