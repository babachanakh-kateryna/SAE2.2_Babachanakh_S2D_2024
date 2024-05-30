package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrapheListe implements Graphe {
    private ArrayList<String> noeuds;
    private ArrayList<Arcs> adjacence;

    public GrapheListe() {
        this.noeuds = new ArrayList<>();
        this.adjacence = new ArrayList<>();
    }

    private int getIndice(String n) {
        return this.noeuds.indexOf(n);
    }

    public void ajouterArc(String depart, String destination, double cout) {
        if (!this.noeuds.contains(depart)) {
            this.noeuds.add(depart);
            this.adjacence.add(new Arcs());
        }
        if (!this.noeuds.contains(destination)) {
            this.noeuds.add(destination);
            this.adjacence.add(new Arcs());
        }
        int index = this.getIndice(depart);
        this.adjacence.get(index).ajouterArc(new Arc(destination, cout));
    }


    @Override
    public List<String> listeNoeuds() {
        return this.noeuds;
    }

    @Override
    public List<Arc> suivants(String n) {
        int index = this.getIndice(n);
        return this.adjacence.get(index).getArcs();
    }


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



}

