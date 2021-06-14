public class Shape {
    public double[][] coors;
    public double[] center;
    public double radius;
    public boolean circle;
    public Shape(double[][] coors, double[] center, double radius, boolean circle){
        this.coors = coors;
        this.center=center;
        this.radius=radius;
        this.circle=circle;
    }
}
