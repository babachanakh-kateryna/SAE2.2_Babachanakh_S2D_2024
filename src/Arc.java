package src;

/**
 * La classe Arc qui represente un arc partant d’un noeud
 */
public class Arc {

    // attributs
    private String dest;
    private double cout;

    /**
     * Constructeur avec deux parametres
     *
     * @param dest le nom du nœud destination de l’arc
     * @param cout le cout (ou poids) de l’arc
     */
    public Arc(String dest, double cout) {
        this.dest = dest;
        // cout doit etre positive
        if (cout>0){
            this.cout = cout;
        }
    }

    /**
     * Methode de presentation visuelle d'informations sur des variables
     *
     * @return string line avec des informations sur les variables
     */
    @Override
    public String toString(){
        return "(" + this.dest + "," + this.cout + ")";
   }

    /**
     * Methode qui renvoie le nom du nœud de destination de l'arc
     *
     * @return le nom du nœud destination de l’arc
     */
   public String getDest(){
        return this.dest;
   }

    /**
     * Methode qui renvoie le cout (ou poids) de l’arc
     *
     * @return le cout (ou poids) de l’arc
     */
   public double getCout() {
        return this.cout;
   }
}
