package src.algo;
import src.Arc;
import src.Graphe;
import src.Valeur;

import java.util.List;

/**
 * La classe BellmanFord contient la methode resoudre qui implemente l'algorithme de Bellman-Ford
 */
public class BellmanFord implements Algo{
    /*

        Fonction resoudre(Graphe g InOut, String depart)

            // Initialisation des valeurs et des parents
            Pour chaque noeud dans noeuds faire
                L(noeud) ← +Infini
                parent(noeud) ← null
            fin pour

            L(depart) ← 0

            Pour i de 1 à nombre de nœuds dans noeuds faire
                Pour chaque noeud dans noeuds faire
                    Pour chaque noeudSuivant dans suivants(noeud) faire
                        Si L(noeud) + poids(noeud, noeudSuivant) < L(noeudSuivant) alors
                            L(noeudSuivant) ← L(noeud) + poids(noeud, noeudSuivant)
                            parent(noeudSuivant) ← noeud
                        fin si
                    fin pour
                fin pour
            fin pour

            Retourner noeuds
        Fin Fonction


        Lexique :
        i : entier, indice d'iteration
        noeud : String, nœud courant pour lequel on teste les différents chemins possibles via sa liste de nœuds
        noeudSuivant : String, nœud adjacent au "noeud" courant
        L(noeud) : Double, fonction qui renvoie la distance minimale vers le nœud
        poids(noeud, noeudSuivant) : Double, fonction qui renvoie le cout de l'arc entre noeud et noeudSuivant
        parent(noeud) : String, parent du noeud qui permet d'atteindre ce noeud avec la meilleure valeur
     */

    /**
     * methode pour resoudre le probleme du plus court chemin a l'aide de l'algorithme de Bellman-Ford
     *
     * @param g      le graphe oriente
     * @param depart le nœud de depart
     * @return un objet Valeur contenant les distances et les parents de chaque nœud apres convergence de l'algorithme
     */
    @Override
    public Valeur resoudre(Graphe g, String depart) {
        Valeur valeur = new Valeur();
        List<String> noeuds = g.listeNoeuds();

        // initialisation des valeurs a +Infini et des parents a null
        for (String noeud : noeuds) {
            valeur.setValeur(noeud, Double.MAX_VALUE);
            valeur.setParent(noeud, null);
        }

        // on met le nom de depart a la valeur 0
        valeur.setValeur(depart, 0);


        // boucle iterate autant de fois qu'il y a de noeud dans le graphe g-1 (ne compte pas le noeud de depart)
        for (int i = 1; i < noeuds.size(); i++) {

            // boucle qui prend les noms d'une liste de nœuds un par un
            for (String noeud : noeuds) {
                double valeurNoeud = valeur.getValeur(noeud);
                if (valeurNoeud != Double.MAX_VALUE) {
                    List<Arc> arcs = g.suivants(noeud);

                    // boucle qui prend les arcs du noeud courant un par un
                    for (Arc arc : arcs) {
                        String noeudSuivant = arc.getDest();
                        double poids = arc.getCout();
                        if (valeurNoeud + poids < valeur.getValeur(noeudSuivant)) {
                            valeur.setValeur(noeudSuivant, valeurNoeud + poids);
                            valeur.setParent(noeudSuivant, noeud);
                        }
                    }
                }
            }
        }

        return valeur;
    }
}