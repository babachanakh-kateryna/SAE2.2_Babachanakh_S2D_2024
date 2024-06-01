package src.algo;

import src.Graphe;
import src.Valeur;

public interface Algo {

    /**
     * Methode pour resoudre le probleme du plus court chemin a l'aide de l'algorithme de Dijkstra
     * @param g le graphe oriente
     * @param depart le nœud de depart
     * @return un objet Valeur contenant les distances et les parents de chaque nœud apres convergence de l'algorithme
     */
    public Valeur resoudre(Graphe g, String depart);
}
