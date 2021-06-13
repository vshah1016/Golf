 public class BallHitLogic {
   //TODO PRAJWAL FIX THIS YOU IDIOT

        public static int[] bounceCheck ( int vert, int hor) //This is the important method here
        {
            Point ballPosition = GolfBall.locations.currentLocation();
            for (int i = 0; i < Main.obstacles.length; i++) {
                int[][] points = CourseGen.isTouching();
                if (points[0][0] != -1) {
                    int changeY = (points[1][1] - points[0][1]);
                    int changeX = (points[1][0] - points[0][0]);
 //Slope of final trajectory = wallSlope/(initialDirection/wallSlope);

                    int diffY = changeX * vert;
                    int diffX = changeY * hor;

                    int newY = diffX * ballPosition.y;
                    int newX = diffY * ballPosition.x;
                    int[] arr = new int[2];
                    arr[0] = newX;
                    arr[1] = newY;
                    return arr;

                }
            }

        }
 }
