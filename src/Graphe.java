package src;

import java.util.List;

public interface Graphe {
    List<String> listeNoeuds();
    List<Arc> suivants(String n);
}
