import java.util.Random;

public class CourseGen {
    static Random rand = new Random();
    static public Shape genHole(){
        //preset to not hit wall by subtracting 25 from 1920 and 1080 and starting at 25
        int rXval = rand.nextInt(1885 - 1100 + 1) + 1100;
        int rYval = rand.nextInt(400 - 25 + 1) + 25;
        Shape shape = new Shape(new double[][]{},new double[]{rXval,rYval},25,true);
        return shape;
    }
    public static Shape[] genObstacle(){
        double[][] coors;
        while(true){

            //TODO factor angle
            Shape[] obsCoors = new Shape[3];
            for(int i=0; i<3; i++){
                coors = new double[2][2];
                int rLength = rand.nextInt(200 - 50 + 1) + 250;
                int rWidth = rand.nextInt(200 - 50 + 1) + 50;
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
            if(!overlap(circle,obsCoors)){
                System.out.println(obsCoors[0].coors[0][0]);
                System.out.println(obsCoors[1].coors[0][0]);
                System.out.println(obsCoors[2].coors[0][0]);
                return new Shape[]{circle,obsCoors[0],obsCoors[1],obsCoors[2]};

            }
        }
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
        for(int i=0; i<3;i++){
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
