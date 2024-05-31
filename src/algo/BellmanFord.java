package src.algo;

public class BellmanFord {
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

}