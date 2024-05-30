package src;

import java.util.ArrayList;
import java.util.List;

/**
 *  La classe Arcs gere une collection d'arcs
 */
public class Arcs {

    // attributs
    private List<Arc> arcs;

    /**
     * Constructeur qui initialise une liste vide d'arcs
     */
    public Arcs() {
        this.arcs = new ArrayList<>();
    }

    /**
     * Ajoute un arc a la liste des arcs
     *
     * @param a l'arc a ajouter a la liste
     */
    public void ajouterArc(Arc a) {
        this.arcs.add(a);
    }

    /**
     * Methode qui retourne la liste des arcs
     *
     * @return la liste des arcs
     */
    public List<Arc> getArcs() {
        return this.arcs;
    }

    /**
     * Methode de presentation visuelle d'informations sur de la liste des arcs
     *
     * @return une chaine representant la liste des arcs
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Arc arc : arcs) {
            res.append(arc.getDest()).append("(").append(arc.getCout()).append(") ");
        }
        return res.toString();
    }

}
