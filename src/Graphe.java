package src;

import java.util.List;

/**
 * Interface Graphe qui definit les methodes pour manipuler des graphes
 */
public interface Graphe {
    /**
     * Methode qui retourne la liste de tous les noeuds du graphe
     *
     * @return une liste de chaines representant les noeuds du graphe
     */
    List<String> listeNoeuds();

    /**
     * Methode qui retourne la liste des arcs partant du noeud specifie
     *
     * @param n le nom du noeud dont on veut obtenir les arcs suivants
     * @return une liste d'objets Arc representant les arcs partant du noeud donne
     */
    List<Arc> suivants(String n);
}
