package src.algo;

import src.Arc;
import src.Graphe;
import src.Valeur;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
    /*

        Entrees :
        G un graphe oriente avec une ponderation positive des arcs (cout ou poids)
        A un sommet (depart) de G

        Debut
        Q <- {} // utilisation d’une liste de noeuds a traiter
        Pour chaque sommet v de G faire
            v.valeur <- Infini
            v.parent <- Indefini
            Q <- Q U {v} // ajouter le sommet v a la liste Q
        Fin Pour

        A.valeur <- 0
        Tant que Q est un ensemble non vide faire
            u <- un sommet de Q telle que u.valeur est minimal
            // enlever le sommet u de la liste Q
            Q <- Q \ {u}
            Pour chaque sommet v de Q tel que l’arc (u,v) existe faire
                d <- u.valeur + poids(u,v)
                Si d < v.valeur
                       // le chemin est plus interessant
                        Alors v.valeur <- d
                              v.parent <- u
                Fin Si
            Fin Pour
        Fin Tant que
        Fin
     */

    /**
     * Methode pour resoudre le probleme du plus court chemin a l'aide de l'algorithme de Dijkstra
     * @param g le graphe oriente
     * @param depart le nœud de depart
     * @return un objet Valeur contenant les distances et les parents de chaque nœud apres convergence de l'algorithme
     */
    public Valeur resoudre(Graphe g, String depart) {
        Valeur valeur = new Valeur();
        List<String> noeuds = g.listeNoeuds();
        List<String> Q = new ArrayList<>(noeuds);

        // Initialisation des valeurs et des parents
        for (String noeud : noeuds) {
            valeur.setValeur(noeud, Double.MAX_VALUE);
            valeur.setParent(noeud, null);
        }
        valeur.setValeur(depart, 0);

        // Traitement des nœuds
        while (!Q.isEmpty()) {
            // Trouver le nœud u avec la plus petite valeur
            String u = Q.get(0);
            for (String noeud : Q) {
                if (valeur.getValeur(noeud) < valeur.getValeur(u)) {
                    u = noeud;
                }
            }
            Q.remove(u);

            // Mettre a jour les valeurs des voisins de u
            List<Arc> arcs = g.suivants(u);
            for (Arc arc : arcs) {
                String v = arc.getDest();
                double poids = arc.getCout();
                double nouvelleValeur = valeur.getValeur(u) + poids;

                if (nouvelleValeur < valeur.getValeur(v)) {
                    valeur.setValeur(v, nouvelleValeur);
                    valeur.setParent(v, u);
                }
            }
        }

        return valeur;
    }
}
