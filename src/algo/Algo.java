package src.algo;

import src.Graphe;
import src.Valeur;

/**
 * Interface Algo representant un algorithme de recherche de plus court chemin dans un graphe
 */
public interface Algo {

    /**
     * Methode pour resoudre le probleme du plus court chemin a l'aide de l'algorithme de Dijkstra
     * @param g le graphe oriente
     * @param depart le nœud de depart
     * @return un objet Valeur contenant les distances et les parents de chaque nœud apres convergence de l'algorithme
     */
    public Valeur resoudre(Graphe g, String depart);
}
