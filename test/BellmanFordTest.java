package test;

import org.junit.jupiter.api.Test;
import src.GrapheListe;
import src.Valeur;
import src.algo.BellmanFord;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour l'algorithme de Bellman-Ford et la classe Valeur
 */
public class BellmanFordTest {

    /**
     * verifie que les valeurs sont correctement initialisees et recuperees
     */
    @Test
    public void test1_ValeurConstructor_OK() {
        // preparation des donnees
        Valeur valeur = new Valeur();
        valeur.setValeur("A", 10.0);
        // verifications
        assertEquals(10.0, valeur.getValeur("A"));
    }

    /***
     * verifie que la chaîne retournee est correcte
     */
    @Test
    public void test2_ValeurToString_OK() {
        // preparation des donnees
        Valeur valeur = new Valeur();
        valeur.setValeur("A", 10.0);
        valeur.setParent("A", "B");
        String expectedOutput = "A ->  V:10.0 p:B\n";

        // verifications
        assertEquals(expectedOutput, valeur.toString());
    }

    /**
     * verifie que le chemin calcule est correct
     */
    @Test
    public void test3_ValeurCalculerChemin_0K() {
        // preparation des donnees
        Valeur valeur = new Valeur();
        valeur.setParent("A", "B");
        valeur.setParent("B", "C");
        valeur.setParent("C", null);

        // methode testee
        List<String> chemin = valeur.calculerChemin("A");

        // verifications
        assertEquals(3, chemin.size());
        assertEquals("C", chemin.get(0));
        assertEquals("B", chemin.get(1));
        assertEquals("A", chemin.get(2));
    }

    /**
     * verifie que l’algorithme du point fixe est correct et que les parents des nœuds sont bien calcules
     */
    @Test
    public void test4_Resoudre_OK() {

        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("B", "E", 11);
        graphe.ajouterArc("C", "A", 19);
        graphe.ajouterArc("D", "B", 23);
        graphe.ajouterArc("D", "C", 10);
        graphe.ajouterArc("E", "D", 43);

        // methode testee
        BellmanFord bellmanFord = new BellmanFord();
        Valeur valeur = bellmanFord.resoudre(graphe, "A");

        // verifications
        // des valeurs de distance
        assertEquals(0.0, valeur.getValeur("A"), "La distance de A a A doit etre 0");
        assertEquals(12.0, valeur.getValeur("B"), "La distance de A a B doit etre 12");
        assertEquals(76.0, valeur.getValeur("C"), "La distance de A a C doit etre 76");
        assertEquals(66.0, valeur.getValeur("D"), "La distance de A a D doit etre 66");
        assertEquals(23.0, valeur.getValeur("E"), "La distance de A a E doit etre 23");

        // des parents
        assertNull(valeur.getParent("A"), "A ne doit pas avoir de parent");
        assertEquals("A", valeur.getParent("B"), "Le parent de B doit etre A");
        assertEquals("D", valeur.getParent("C"), "Le parent de C doit etre D");
        assertEquals("E", valeur.getParent("D"), "Le parent de D doit etre B");
        assertEquals("B", valeur.getParent("E"), "Le parent de E doit etre B");

        // Verification du chemin de A a C
        List<String> cheminAttendu = Arrays.asList("A", "B", "E", "D", "C");
        assertEquals(cheminAttendu, valeur.calculerChemin("C"), "Le chemin de A a C doit etre [A, B, E, D, C]");
    }

    /**
     * verifie que l’algorithme du point fixe est correct et que les parents des nœuds sont bien calcules
     * a partir du fichier
     *
     * @throws IOException si une erreur d'E/S se produit
     */
    @Test
    public void test5_Resoudre_FileOK() throws IOException {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe("Graphes/Graphe_exemple1.txt");

        // methode testee
        BellmanFord bellmanFord = new BellmanFord();
        Valeur valeur = bellmanFord.resoudre(graphe, "A");

        // verifications
        // des valeurs de distance
        assertEquals(0.0, valeur.getValeur("A"), "La distance de A a A doit etre 0");
        assertEquals(12.0, valeur.getValeur("B"), "La distance de A a B doit etre 12");
        assertEquals(76.0, valeur.getValeur("C"), "La distance de A a C doit etre 76");
        assertEquals(66.0, valeur.getValeur("D"), "La distance de A a D doit etre 66");
        assertEquals(23.0, valeur.getValeur("E"), "La distance de A a E doit etre 23");

        // des parents
        assertNull(valeur.getParent("A"), "A ne doit pas avoir de parent");
        assertEquals("A", valeur.getParent("B"), "Le parent de B doit etre A");
        assertEquals("D", valeur.getParent("C"), "Le parent de C doit etre D");
        assertEquals("E", valeur.getParent("D"), "Le parent de D doit etre B");
        assertEquals("B", valeur.getParent("E"), "Le parent de E doit etre B");

        // Verification du chemin de A a C
        List<String> cheminAttendu = Arrays.asList("A", "B", "E", "D", "C");
        assertEquals(cheminAttendu, valeur.calculerChemin("C"), "Le chemin de A a C doit etre [A, B, E, D, C]");

    }

    /**
     * Teste l'algorithme de Bellman-Ford sur un graphe avec des poids negatifs
     */
    @Test
    public void test6_Resoudre_NegativeCout() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "B", 1);
        graphe.ajouterArc("B", "C", -1);
        graphe.ajouterArc("C", "D", -1);


        // methode testee
        BellmanFord bellmanFord = new BellmanFord();
        Valeur valeur = bellmanFord.resoudre(graphe, "A");

        // verifications
        assertEquals(0.0, valeur.getValeur("A"), "La distance de A a A doit etre 0");
        assertEquals(1.0, valeur.getValeur("B"), "La distance de A a B doit etre 1");
        assertEquals(1.0, valeur.getValeur("C"), "La distance de A a C doit etre 1");
        assertEquals(1.0, valeur.getValeur("D"), "La distance de A a D doit etre 1");

        // des parents
        assertNull(valeur.getParent("A"), "A ne doit pas avoir de parent");
        assertEquals("A", valeur.getParent("B"), "Le parent de B doit etre A");
        assertEquals("B", valeur.getParent("C"), "Le parent de C doit etre B");
        assertEquals("C", valeur.getParent("D"), "Le parent de D doit etre C");

    }

    /**
     * Teste l'algorithme de Bellman-Ford sur un graphe avec des nœuds deconnectes
     **/
    @Test
    public void test7_Resoudre_DisconnectedNodes() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "B", 5);
        graphe.ajouterArc("A", "C", 10);
        graphe.ajouterArc("D", "E", 3);  // Noeuds D et E sont deconnectes de A, B, C

        // methode testee
        BellmanFord bellmanFord = new BellmanFord();
        Valeur valeur = bellmanFord.resoudre(graphe, "A");

        // verifications
        assertEquals(0.0, valeur.getValeur("A"), "La distance de A a A doit etre 0");
        assertEquals(5.0, valeur.getValeur("B"), "La distance de A a B doit etre 5");
        assertEquals(10.0, valeur.getValeur("C"), "La distance de A a C doit etre 10");
        assertEquals(Double.MAX_VALUE, valeur.getValeur("D"), "La distance de A a D doit etre Double.MAX_VALUE");
        assertEquals(Double.MAX_VALUE, valeur.getValeur("E"), "La distance de A a E doit etre Double.MAX_VALUE");

        // des parents
        assertNull(valeur.getParent("A"), "A ne doit pas avoir de parent");
        assertEquals("A", valeur.getParent("B"), "Le parent de B doit etre A");
        assertEquals("A", valeur.getParent("C"), "Le parent de C doit etre A");
        assertNull(valeur.getParent("D"), "D ne doit pas avoir de parent");
        assertNull(valeur.getParent("E"), "E ne doit pas avoir de parent");
    }

    /**
     * Teste l'algorithme de Bellman-Ford sur un graphe avec des boucles
     */
    @Test
    public void test8_Resoudre_Boucles() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "A", 2); // Boucles
        graphe.ajouterArc("A", "B", 5);
        graphe.ajouterArc("B", "C", 2);

        // methode testee
        BellmanFord bellmanFord = new BellmanFord();
        Valeur valeur = bellmanFord.resoudre(graphe, "A");

        // verifications
        assertEquals(0.0, valeur.getValeur("A"), "La distance de A a A doit etre 0");
        assertEquals(5.0, valeur.getValeur("B"), "La distance de A a B doit etre 5");
        assertEquals(7.0, valeur.getValeur("C"), "La distance de A a C doit etre 7");

        // des parents
        assertNull(valeur.getParent("A"), "A ne doit pas avoir de parent");
        assertEquals("A", valeur.getParent("B"), "Le parent de B doit etre A");
        assertEquals("B", valeur.getParent("C"), "Le parent de C doit etre B");
    }

    /**
     * Teste l'algorithme de Bellman-Ford sur un graphe avec invalid entrees
     */
    @Test
    public void test9_Resoudre_InvalidInput() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();

        // methode testee
        BellmanFord bellmanFord = new BellmanFord();

        // verifications
        assertThrows(NullPointerException.class, () -> bellmanFord.resoudre(graphe, null),
                "Un point de depart null doit lancer une NullPointerException");
        assertThrows(NullPointerException.class, () -> bellmanFord.resoudre(null, "A"),
                "Un graphe null doit lancer une NullPointerException");
    }
}
