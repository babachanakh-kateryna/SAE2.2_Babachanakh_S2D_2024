package src;

public class Arc {
    private String dest;
    private double cout;

    public Arc(String dest, double cout) {
        this.dest = dest;
        this.cout = cout;
    }

    @Override
    public String toString(){
        return "(" + this.dest + "," + this.cout + ")";
   }

   public String getDest(){
        return this.dest;
   }

   public double getCout() {
        return this.cout;
    }
}
