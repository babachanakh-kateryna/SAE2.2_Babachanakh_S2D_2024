package test;

import org.junit.jupiter.api.Test;
import src.GrapheListe;
import src.Valeur;
import src.algo.BellmanFord;
import src.algo.Dijkstra;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour l'algorithme de Dijkstra
 */
public class DijkstraTest {

    /**
     * verifie que l’algorithme Dijkstra est correct et que les parents des nœuds sont bien calcules
     */
    @Test
    public void test1_Resoudre_OK() {
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
        Dijkstra dijkstra = new Dijkstra();
        Valeur valeur = dijkstra.resoudre(graphe, "A");

        // verifications
        assertEquals(0.0, valeur.getValeur("A"));
        assertEquals(12.0, valeur.getValeur("B"));
        assertEquals(76.0, valeur.getValeur("C"));
        assertEquals(66.0, valeur.getValeur("D"));
        assertEquals(23.0, valeur.getValeur("E"));

        // des parents
        assertNull(valeur.getParent("A"));
        assertEquals("A", valeur.getParent("B"));
        assertEquals("D", valeur.getParent("C"));
        assertEquals("E", valeur.getParent("D"));
        assertEquals("B", valeur.getParent("E"));

        // Verification du chemin de A a C
        List<String> cheminAttendu = Arrays.asList("A", "B", "E", "D", "C");
        assertEquals(cheminAttendu, valeur.calculerChemin("C"));
    }

    /**
     * verifie que l’algorithme Dijkstra est correct et que les parents des nœuds sont bien calcules
     * a partir du fichier
     *
     * @throws IOException si une erreur d'E/S se produit
     */
    @Test
    public void test2_Resoudre_Fichier() throws IOException {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe("Graphes/Graphe_exemple1.txt");

        // methode testee
        Dijkstra dijkstra = new Dijkstra();
        Valeur valeur = dijkstra.resoudre(graphe, "A");

        // verifications
        assertEquals(0.0, valeur.getValeur("A"));
        assertEquals(12.0, valeur.getValeur("B"));
        assertEquals(76.0, valeur.getValeur("C"));
        assertEquals(66.0, valeur.getValeur("D"));
        assertEquals(23.0, valeur.getValeur("E"));

        // des parents
        assertNull(valeur.getParent("A"));
        assertEquals("A", valeur.getParent("B"));
        assertEquals("D", valeur.getParent("C"));
        assertEquals("E", valeur.getParent("D"));
        assertEquals("B", valeur.getParent("E"));

        // Verification du chemin de A a C
        List<String> cheminAttendu = Arrays.asList("A", "B", "E", "D", "C");
        assertEquals(cheminAttendu, valeur.calculerChemin("C"));
    }

    /**
     * Teste l'algorithme de Dijkstra sur un graphe avec des poids negatifs
     */
    @Test
    public void test3_Resoudre_NegativeCout() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "B", 1);
        graphe.ajouterArc("B", "C", -1);
        graphe.ajouterArc("C", "D", -1);


        // methode testee
        Dijkstra dijkstra = new Dijkstra();
        Valeur valeur = dijkstra.resoudre(graphe, "A");

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
     * Teste l'algorithme de Dijkstra sur un graphe avec des nœuds deconnectes
     **/
    @Test
    public void test4_Resoudre_DisconnectedNodes() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "B", 5);
        graphe.ajouterArc("A", "C", 10);
        graphe.ajouterArc("D", "E", 3);  // Noeuds D et E sont deconnectes de A, B, C

        // methode testee
        Dijkstra dijkstra = new Dijkstra();
        Valeur valeur = dijkstra.resoudre(graphe, "A");

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
     * Teste l'algorithme de Dijkstra sur un graphe avec des boucles
     */
    @Test
    public void test5_Resoudre_Boucles() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "A", 2); // Boucles
        graphe.ajouterArc("A", "B", 5);
        graphe.ajouterArc("B", "C", 2);

        // methode testee
        Dijkstra dijkstra = new Dijkstra();
        Valeur valeur = dijkstra.resoudre(graphe, "A");

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
     * Teste l'algorithme de Dijkstra sur un graphe avec invalid entrees
     */
    @Test
    public void test6_Resoudre_InvalidInput() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();

        // methode testee
        Dijkstra dijkstra = new Dijkstra();

        // verifications
        assertThrows(NullPointerException.class, () -> dijkstra.resoudre(graphe, null),
                "Un point de depart null doit lancer une NullPointerException");
        assertThrows(NullPointerException.class, () -> dijkstra.resoudre(null, "A"),
                "Un graphe null doit lancer une NullPointerException");
    }
}
