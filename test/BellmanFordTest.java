package test;

import org.junit.jupiter.api.Test;
import src.GrapheListe;
import src.Valeur;
import src.algo.BellmanFord;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BellmanFordTest {

    /**
     * verifie que l’algorithme du point fixe est correct et que les parents des nœuds sont bien calcules
     */
    @Test
    public void test_Resoudre_OK() {

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
    }

    /**
     * verifie que l’algorithme du point fixe est correct et que les parents des nœuds sont bien calcules
     * a partir du fichier
     *
     * @throws IOException si une erreur d'E/S se produit
     */
    @Test
    public void test_Resoudre_FileOK() throws IOException {
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

    }
}
