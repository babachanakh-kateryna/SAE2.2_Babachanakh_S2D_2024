package src.main;

import src.GrapheListe;
import src.Valeur;
import src.algo.Algo;
import src.algo.BellmanFord;
import src.algo.Dijkstra;

import java.io.IOException;

/**
 * programme principal pour utilisation l'algorithme de Dijkstra
 */
public class MainDijkstra {

    public static void main(String[] args) {
        try {
            System.out.println("Valeurs des distances et des parents apres l'application de l'algorithme de Dijkstra:");

            System.out.println("Saisie de donnees par la methode ajouterArc");
            // creation du graphe
            GrapheListe graphe = new GrapheListe();

            // ajout des arcs au graphe
            graphe.ajouterArc("A", "B", 12);
            graphe.ajouterArc("A", "D", 87);
            graphe.ajouterArc("B", "E", 11);
            graphe.ajouterArc("C", "A", 19);
            graphe.ajouterArc("D", "B", 23);
            graphe.ajouterArc("D", "C", 10);
            graphe.ajouterArc("E", "D", 43);

            // Affichage du graphe
            System.out.println("Graphe:");
            System.out.println(graphe);

            // Calcul des chemins les plus courts a partir du nœud "A"
            Algo algo = new Dijkstra();
            Valeur valeur = algo.resoudre(graphe, "A");

            // Affichage des valeurs de distance et des parents pour chaque nœud
            System.out.println("Distances et parents après application de l'algorithme de Dijkstra:");
            System.out.println(valeur.toString());

            // Affichage des chemins pour des nœuds donnes
            String[] noeudsDestionation = {"B", "C", "D", "E"};
            for (String destination : noeudsDestionation) {
                System.out.println("Chemin de A à " + destination + ": " + valeur.calculerChemin(destination));
            }


            System.out.println("\nSaisie de donnees a l'aide d'un fichier");
            GrapheListe graphefile = new GrapheListe("Graphes/Graphe_exemple1.txt");
            // Affichage du graphe
            System.out.println("Graphe:");
            System.out.println(graphefile.toString());
            // Calcul des chemins les plus courts a partir du nœud "A"
            Algo algoFile = new Dijkstra();
            Valeur valeurFile = algoFile.resoudre(graphe, "A");

            // Affichage des valeurs de distance et des parents pour chaque nœud
            System.out.println("Distances et parents après application de l'algorithme de Dijkstra:");
            System.out.println(valeurFile.toString());

            // Affichage des chemins pour des nœuds donnes
            String[] noeudsDestionationFile = {"B", "C", "D", "E"};
            for (String destination : noeudsDestionationFile) {
                System.out.println("Chemin de A à " + destination + ": " + valeurFile.calculerChemin(destination));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

