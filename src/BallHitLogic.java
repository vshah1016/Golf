public class BallHitLogic {
    
      public int[] bounceCheck(int vert, int hor) //This is the important method here
      {
          int newX;
          int newY;
         //x and y are current coordinates of ball
         if(x < 25 || x > 1895)
         {
            newX = -1*hor;
            newY = vert;
         }
         if(y < 25 || y > 1895)
         {
            newX = hor;
            newY = -1*vert;
         }
         for(int i = 0; i < obstacles.size(); i++)
         {
            int [][] points = hitting();
            if(points[0][0] != -1) 
            {
               int changeY = (points[1][1] - points[0][1]);
               int changeX = (points[1][0]-points[0][0]); 
               if(changeX == 0)
               {
                  newX = - 1* hor;
                  newY = vert;
               }
               else if(changeY == 0)
               {
                  newX = hor;
                  newY = -1*vert;
               }
               else
               {
                  //Slope of final trajectory = wallSlope/(initialDirection/wallSlope);

                  int diffY = changeX*vert;
                  int diffX = changeY*hor;

                  newY = diffX*y;
                  newX = diffY*x;
               }
               break;

            }
         }
         int [] arr = new int[2];
         arr[0] = newX;
         arr[1] = -newY;
         return arr;

      }
}
