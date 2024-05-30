package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe GrapheListe implemente l’interface Graphe
 * et permet de representer les donnees associees a un graphe
 */
public class GrapheListe implements Graphe {

    // attributs
    private ArrayList<String> noeuds;
    private ArrayList<Arcs> adjacence;

    /**
     * Constructeur qui initialise les listes de noeuds et d'adjacence
     */
    public GrapheListe() {
        this.noeuds = new ArrayList<>();
        this.adjacence = new ArrayList<>();
    }

    /**
     * Methode qui retourne l’indice du noeud n de la liste noeuds
     *
     * @param n le nom du noeud
     * @return l'indice du noeud dans la liste
     */
    public int getIndice(String n) {
        return this.noeuds.indexOf(n);
    }

    /**
     * Methode qui ajoute un arc au graphe
     *
     * @param depart depart le noeud
     * @param destination destination le noeud
     * @param cout le cout (ou poids) de l’arc
     */
    public void ajouterArc(String depart, String destination, double cout) {

        // Ajouter un nœud de depart s'il n'est pas present
        if (!this.noeuds.contains(depart)) {
            this.noeuds.add(depart);
            this.adjacence.add(new Arcs());
        }

        // Ajouter un nœud de destination s'il n'est pas present
        if (!this.noeuds.contains(destination)) {
            this.noeuds.add(destination);
            this.adjacence.add(new Arcs());
        }

        // Recuperer l'index du nœud de depart
        int index = this.getIndice(depart);

        List<Arc> arcs = this.adjacence.get(index).getArcs();

        // Recherche d'arcs en double
        for (Arc arc : arcs) {
            if (arc.getDest().equals(destination) && arc.getCout() == cout) {
                return; // Arc en double, ne pas ajouter
            }
        }

        // Ajouter l'arc a la liste de adjacence du nœud de depart
        this.adjacence.get(index).ajouterArc(new Arc(destination, cout));
    }

    /**
     * Constructeur qui initialise le graphe a partir d'un fichier
     *
     * @param filename le nom du fichier contenant les arcs du graphe
     * @throws IOException si une erreur d'E/S se produit
     */
    public GrapheListe(String filename) throws IOException {
        this();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts.length == 3) {
                String depart = parts[0];
                String destination = parts[1];
                double cout = Double.parseDouble(parts[2]);
                ajouterArc(depart, destination, cout);
            }
        }
        reader.close();
    }

    /**
     * Methode qui retourne la liste de tous les noeuds du graphe
     *
     * @return une liste de chaines representant les noeuds du graphe
     */
    @Override
    public List<String> listeNoeuds() {
        return this.noeuds;
    }

    /**
     * Methode qui retourne la liste des arcs partant du noeud specifie
     *
     * @param n le nom du noeud dont on veut obtenir les arcs suivants
     * @return une liste d'objets Arc representant les arcs partant du noeud donne
     */
    @Override
    public List<Arc> suivants(String n) {
        int index = this.getIndice(n);
        return this.adjacence.get(index).getArcs();
    }


    /**
     * Methode qui retourne une representation sous forme de chaine du graphe
     *
     * @return une chaine representant le graphe
     */
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.noeuds.size(); i++) {
            String noeud = this.noeuds.get(i);
            res.append(noeud).append(" -> ");
            List<Arc> arcs = this.adjacence.get(i).getArcs();
            for (Arc arc : arcs) {
                res.append(arc.getDest()).append("(").append(arc.getCout()).append(") ");
            }
            res.append("\n");
        }
        return res.toString();
    }

    /**
     * Methode principale pour tester la classe GrapheList
     *
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        try {

            System.out.println("Saisie de donnees par la methode ajouterArc");
            GrapheListe graphe = new GrapheListe();
            graphe.ajouterArc("A", "B", 12);
            graphe.ajouterArc("A", "D", 87);
            graphe.ajouterArc("B", "E", 11);
            graphe.ajouterArc("C", "A", 19);
            graphe.ajouterArc("D", "B", 23);
            graphe.ajouterArc("D", "C", 10);
            graphe.ajouterArc("E", "D", 43);

            System.out.println(graphe.toString());

            System.out.println("Saisie de donnees a l'aide d'un fichier");
            GrapheListe graphefile = new GrapheListe("Graphes/Graphe_exemple1.txt");
            System.out.println(graphefile.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

