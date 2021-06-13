public class Bounce {
    public static int[] bounceCheck(int lilUzi, int horizontal) {
        Point ballPosition = GolfBall.locations.currentLocation();
        int impactDirection;
        double[] coords = new double[]{ballPosition.x, ballPosition.y};
         double[] isTouching = isTouching(new Shape(null, coords, 25, true), Main.obstacles);
         int[][] points = new int[][]{
                 new int[]{
                         (int) isTouching[0], (int) isTouching[1]
                 },
                 new int[]{
                         (int) isTouching[2], (int) isTouching[3]
                 }
         };
         if(points[0][0] != -1) {
            int changeY = (points[1][1] - points[0][1]);
            int changeX = (points[1][0]-points[0][0]);
            int newX, newY;
            if(changeX == 0) {
               newX = - 1* horizontal;
               newY = lilUzi;
               impactDirection = 0;
            }
            else if(changeY == 0) {
               newX = horizontal;
               newY = -1*lilUzi;
               impactDirection = 1;
            }
            else {
               //Slope of final trajectory = wallSlope/(initialDirection/wallSlope);
   
               int diffY = changeX*lilUzi;
               int diffX = changeY*horizontal;
         
               newY = diffX* ballPosition.y;
               newX = diffY* ballPosition.x;
               impactDirection = 0;
            }
            int[] arr = new int[4];
            arr[0] = newX;
            arr[1] = newY;
            arr[2] = ballPosition.y;
            arr[4] = impactDirection;
            return arr;
         }
         return new int[]{-1};
   }

    public static double[] isTouching(Shape currCircle, Shape[] Obstacles) {
        int radius = 25;
        double[] center = currCircle.center;
        for (Shape obs : Obstacles) {
            double[][] coors = obs.coors;
            for (int i = (int) coors[0][1]; i < coors[1][1]; i++) {
                if (Math.pow((coors[0][0] - center[0]), 2) + Math.pow((i - center[1]), 2) == Math.pow(radius, 2)) {
                    return new double[]{coors[0][0], coors[0][1], coors[0][0], coors[1][1]};
                }
            }
            for (int i = (int) coors[0][1]; i < coors[1][1]; i++) {
                if (Math.pow((coors[1][0] - center[0]), 2) + Math.pow((i - center[1]), 2) == Math.pow(radius, 2)) {
                    return new double[]{coors[1][0], coors[0][1], coors[1][0], coors[1][1]};
                }
            }
            for (int i = (int) coors[1][0]; i < coors[0][0]; i++) {
                if (Math.pow((i - center[0]), 2) + Math.pow(coors[0][1], 2) == Math.pow(radius, 2)) {
                    return new double[]{coors[0][0], coors[1][1], coors[1][0], coors[1][1]};
                }
            }
            for (int i = (int) coors[1][0]; i < coors[0][0]; i++) {
                if (Math.pow((i - center[0]), 2) + Math.pow(coors[1][1], 2) == Math.pow(radius, 2)) {
                    return new double[]{coors[0][0], coors[0][0], coors[1][0], coors[0][0]};
                }
            }
        }
        if ((center[0] + 25 > 1920)) {
            return new double[]{1920, 1080, 1920, 0};
        }
        if ((center[0] - 25 < 0)) {
            return new double[]{0, 0, 0, 1080};
        }
        if ((center[1] + 25 > 1080)) {
            return new double[]{0, 1080, 1920, 1080};
        }
        if ((center[1] - 25 < 0)) {
            return new double[]{0, 0, 1920, 0};
        }

        return new double[]{-1, -1, -1, -1};
    }
}
//return as x1,y1,x2,y2 @Wal
