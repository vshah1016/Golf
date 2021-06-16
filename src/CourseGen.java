import java.util.Random;

public class CourseGen {
    static Random rand = new Random();
    static public Shape genHole(){
        //preset to not hit wall by subtracting 25 from 1920 and 1080 and starting at 25
        int rXval = rand.nextInt(1885 - 1600 + 1) + 1600;
        int rYval = rand.nextInt(200 - 25 + 1) + 25;
        Shape shape = new Shape(new double[][]{},new double[]{rXval,rYval},25,true);
        return shape;
    }
    public static Shape[] genObstacle(){

        while(true){
            double[][] coors;
            //TODO factor angle
            Shape[] obsCoors = new Shape[6];
            for(int i=0; i<6; i++){
                coors = new double[2][2];
                int rLength;
                int rWidth;
                if(i == 0 || i == 1|| i==2)
                {
                    rLength = rand.nextInt(200 - 50 + 1) + 50;
                    rWidth = rand.nextInt(200 - 50 + 1) + 250;
                }
                else
                {
                    rLength = rand.nextInt(200 - 50 + 1) + 250;
                    rWidth = rand.nextInt(200 - 50 + 1) + 80;
                }
                int rXVal = rand.nextInt(1910 - 10 + 1) + 10;
                int rYVal = rand.nextInt(1070 - 10 + 1) + 10;
                coors[0][0] = rXVal;
                coors[0][1] = rYVal;
                coors[1][0] = rXVal + rWidth;
                coors[1][1] = rYVal + rLength;
                Shape obs = new Shape(coors, new double[]{0}, 0, false);
                obsCoors[i]=obs;
            }
            Shape circle = genHole();
            if(!overlap(circle,obsCoors) && !drootOverlap(circle,obsCoors)){
                return new Shape[]{circle,obsCoors[0],obsCoors[1],obsCoors[2],obsCoors[3], obsCoors[4], obsCoors[5]};

            }
        }
    }
    static public boolean drootOverlap(Shape hole, Shape [] obsCoors)
    {
            double [] center = hole.center;
            double x = center[0];
            double y = center[1];
        for(Shape obs: obsCoors)
        {
            double [][] coors = obs.coors;
            if(!(Math.abs(coors[1][0]-x) > 25 && Math.abs(coors[0][0]-x) > 25 && Math.abs(coors[0][1]-y) > 25 && Math.abs(coors[1][1]-y) > 25))
                return true;
        }
        return false;
    }
    //use overlap to check if wall, obstacle, and hole are touching but also repurpose to check if ball in hole
    static public boolean overlap(Shape hole, Shape[] obsCoors){
        double[] center = hole.center;
        double radius = hole.radius;
        for(Shape obs: obsCoors) {
            double[][] coors = obs.coors;
            if (coors[1][0] >= 1920 || coors[1][1] >= 1080) {
                return true;
            }
            if(coors[0][0]<center[0]+30&&coors[1][0]>center[0]-30&&coors[0][1]<center[1]+30&&coors[1][1]>center[1]-30){return true;}
            for (int i = (int) coors[0][1]; i < coors[1][1]; i++) {
                if (Math.pow((coors[0][0] - center[0]), 2) + Math.pow((i - center[1]), 2) == Math.pow(radius, 2)) {
                    return true;
                }
            }
            for (int i = (int) coors[0][1]; i < coors[1][1]; i++) {
                if (Math.pow((coors[1][0] - center[0]), 2) + Math.pow((i - center[1]), 2) == Math.pow(radius, 2)) {
                    return true;
                }
            }
            for (int i = (int) coors[1][0]; i < coors[0][0]; i++) {
                if (Math.pow((i - center[0]), 2) + Math.pow(coors[0][1], 2) == Math.pow(radius, 2)) {
                    return true;
                }
            }
            for (int i = (int) coors[1][0]; i < coors[0][0]; i++) {
                if (Math.pow((i - center[0]), 2) + Math.pow(coors[1][1], 2) == Math.pow(radius, 2)) {
                    return true;
                }
            }
        }
        for(Shape obs: obsCoors){
            double[][] coors = obs.coors;
            if((925>coors[1][1]&&875<coors[1][1])&&(935<coors[0][0]&&985>coors[0][0])){return true;}
            if((925>coors[0][1]&&875<coors[0][1])&&(935<coors[0][0]&&985>coors[0][0])){return true;}
            if((925>coors[1][1]&&875<coors[1][1])&&(935<coors[1][0]&&985>coors[1][0])){return true;}
            if((925>coors[0][1]&&875<coors[0][1])&&(935<coors[1][0]&&985>coors[1][0])){return true;}
        }
        for(int i=0; i<6;i++){
            double[][] compare1 = obsCoors[i].coors;
            double[][] compare2;
            try{compare2 = obsCoors[i+1].coors;}
            catch(Exception e){compare2 = obsCoors[0].coors;}
            if((compare2[1][1]>compare1[1][1]&&compare2[0][1]<compare1[1][1])&&(compare2[0][0]<compare1[0][0]&&compare2[0][1]>compare1[0][0])){return true;}
            if((compare2[1][1]>compare1[0][1]&&compare2[0][1]<compare1[0][1])&&(compare2[0][0]<compare1[0][0]&&compare2[0][1]>compare1[0][0])){return true;}
            if((compare2[1][1]>compare1[1][1]&&compare2[0][1]<compare1[1][1])&&(compare2[0][0]<compare1[1][0]&&compare2[0][1]>compare1[1][0])){return true;}
            if((compare2[1][1]>compare1[0][1]&&compare2[0][1]<compare1[0][1])&&(compare2[0][0]<compare1[1][0]&&compare2[0][1]>compare1[1][0])){return true;}
        }



        return false;
    }
}
//todo ball obstacle, obstacle obstacle
