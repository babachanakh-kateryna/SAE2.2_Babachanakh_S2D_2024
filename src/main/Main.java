package src.main;

import src.GrapheListe;
import src.Valeur;
import src.algo.BellmanFord;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Valeurs des distances et des parents apres l'application de l'algorithme de Bellman-Ford:");

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

            // Application de l'algorithme de Bellman-Ford
            BellmanFord bellmanFord = new BellmanFord();
            Valeur valeur = bellmanFord.resoudre(graphe, "A");

            // Affichage des valeurs de distance pour chaque nœud
            System.out.println(valeur);


            System.out.println("Saisie de donnees a l'aide d'un fichier");
            GrapheListe graphefile = new GrapheListe("Graphes/Graphe_exemple1.txt");
            // Affichage du graphe
            System.out.println("Graphe:");
            System.out.println(graphefile.toString());
            // Application de l'algorithme de Bellman-Ford
            BellmanFord bellmanFordFile = new BellmanFord();
            Valeur valeurFile = bellmanFord.resoudre(graphefile, "A");
            // Affichage des valeurs de distance
            System.out.println(valeurFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
