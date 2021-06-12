public class BallHitLogic {
   //TODO PRAJWAL FIX THIS YOU IDIOT
    public static void uponContact(int power, int vert, int hor) //ratio = y/x
    {
        for (int i = 0; i < power; i++) {
            y += vert;
            x += hor;
            list.add(new Point(x, y);
            bounceCheck();
            render();
            power--;

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
//OBSTACLE HAS PROPERTY BOTTOM LEFT, BOTTOM RIGHT, TOP LEFT, and TOP RIGHT
        public static boolean includes( int x, int y) //DW About this
        {
            int x1 = Math.min(bottomLeft.getY(), topLeft.getY());
            int x2 = Math.max(bottomRight.getY(), topRight.getY());
            int y1 = Math.min(bottomLeft.getX(), bottomRight.getX());
            int y2 = Math.max(bottomLeft.getX(), bottomRight.getX());
           return x >= x1 && x <= x2 && y >= bottomLeft.getY() && y <= topRight.getY();
        }
    }
}