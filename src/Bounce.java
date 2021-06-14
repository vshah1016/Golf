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
            arr[2] = ballPosition.y - (ballPosition.x * newY/newX);
            arr[4] = impactDirection;
            return arr;
         }
         return new int[]{-1};
   }
   public static double[][] ballHittingObstacle(Shape circle, Shape [] Obstacles)
   {
        double [][] pointsConnectingWallOfContact = new double[2][2];
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                pointsConnectingWallOfContact[i][j] = -1;
            }
        }
        int radius = 25;
        double [] center = circle.center;
        double x = center[0];
        double y = center[1];
        for(Shape obs: Obstacles)
        {
            double [][] coors = obs.coors;
            if(radius+x > coors[0][0] && radius+x < coors[1][0] && y > coors[0][1] && y < coors[1][1])
            {
                pointsConnectingWallOfContact[0] = coors[0];
                pointsConnectingWallOfContact[1][0] = coors[0][0];
                pointsConnectingWallOfContact[1][1] = coors[1][1];
            }
            else if(x-radius > coors[0][0] && x-radius < coors[1][0] && y > coors[0][1] && y < coors[1][1])
            {
                pointsConnectingWallOfContact[0][0] = coors[1][0];
                pointsConnectingWallOfContact[0][1] = coors[0][1];
                pointsConnectingWallOfContact[1] = coors[1];
            }
            else if(x > coors[0][0] && x < coors[1][0] && y+radius > coors[0][1] && y+radius < coors[1][1])
            {
                pointsConnectingWallOfContact[0] = coors[0];
                pointsConnectingWallOfContact[1][0] = coors[1][0];
                pointsConnectingWallOfContact[1][1] = coors[0][1];
            }
            else if(x > coors[0][0] && x < coors[1][0] && y-radius > coors[0][1] && y-radius < coors[1][1])
            {
                pointsConnectingWallOfContact[0][0] = coors[0][0];
                pointsConnectingWallOfContact[0][1] = coors[1][1];
                pointsConnectingWallOfContact[1] = coors[1];
            }

        }
        return pointsConnectingWallOfContact;

   }

    public static double[] isTouching(Shape currCircle, Shape[] obstacles) {
        int radius = 25;
        double[] center = currCircle.center;
        for (int j = 1, obstaclesLength = obstacles.length; j < obstaclesLength; j++) {
            Shape obs = obstacles[j];
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
