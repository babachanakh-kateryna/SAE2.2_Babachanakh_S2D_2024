package test;
import org.junit.jupiter.api.Test;
import src.Arc;
import src.GrapheListe;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;

/**
 * La classe GrapheListeTest pour verifier les methodes de GrapheListe
 */
public class GrapheListeTest {

    /**
     * verifie le constructeur de graphe vide
     */
    @Test
    public void test1_Constructeur_VideGraph() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        // verifications
        assertTrue(graphe.listeNoeuds().isEmpty());
    }

    /**
     * verifie que la methode getIndice renvoie correctement les indices de nœuds
     */
    @Test
    public void test2_getIndice_OK() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("C", "D", 43);

        // verifications
        assertEquals(0, graphe.getIndice("A"));
        assertEquals(1, graphe.getIndice("B"));
        assertEquals(2, graphe.getIndice("C"));
        assertEquals(3, graphe.getIndice("D"));
    }

    /**
     * verifie si la liste de tous les nœuds du grap a ete renvoyee correctement
     * Retourne vrai
     */
    @Test
    public void test3_listeNoeuds_True() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("C", "D", 43);

        // methode testee
        List<String> noeuds = graphe.listeNoeuds();

        // verifications
        assertEquals(4, noeuds.size());
        assertTrue(noeuds.contains("A"));
        assertTrue(noeuds.contains("B"));
        assertTrue(noeuds.contains("C"));
        assertTrue(noeuds.contains("D"));
    }

    /**
     * verifie si la liste de tous les nœuds du grap a ete renvoyee correctement
     * Retourne faux
     */
    @Test
    public void test4_listeNoeuds_False() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("C", "D", 43);

        // methode testee
        List<String> noeuds = graphe.listeNoeuds();

        // verifications
        assertEquals(4, noeuds.size());
        assertTrue(noeuds.contains("A"));
        assertTrue(noeuds.contains("B"));
        assertFalse(noeuds.contains("E"));
        assertFalse(noeuds.contains("a"));
    }

    /**
     * verifie que la liste des arcs est renvoyee correctement, a partir du nœud specifie
     */
    @Test
    public void test5_suivants_OK() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("B", "E", 11);


        // verifications
        assertEquals(2, graphe.suivants("A").size());
        assertEquals("B", graphe.suivants("A").get(0).getDest());
        assertEquals(12, graphe.suivants("A").get(0).getCout());
        assertEquals("D", graphe.suivants("A").get(1).getDest());
        assertEquals(87, graphe.suivants("A").get(1).getCout());

        assertEquals(1, graphe.suivants("B").size());
        assertEquals("E", graphe.suivants("B").get(0).getDest());
        assertEquals(11, graphe.suivants("B").get(0).getCout());
    }


    /**
     * verifie si les arcs ont ete ajoutes correctement au graph
     */
    @Test
    public void test6_AjouterArc_OK(){
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();

        // methode testee
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("B", "E", 11);

        // verifications

        // Verifie les noeuds
        assertEquals(4, graphe.listeNoeuds().size());
        assertTrue(graphe.listeNoeuds().contains("A"));
        assertTrue(graphe.listeNoeuds().contains("B"));
        assertTrue(graphe.listeNoeuds().contains("D"));
        assertTrue(graphe.listeNoeuds().contains("E"));

        // Verifie les arcs
        assertEquals(2, graphe.suivants("A").size());
        assertEquals("B", graphe.suivants("A").get(0).getDest());
        assertEquals(12, graphe.suivants("A").get(0).getCout());
        assertEquals("D", graphe.suivants("A").get(1).getDest());
        assertEquals(87, graphe.suivants("A").get(1).getCout());

        assertEquals(1, graphe.suivants("B").size());
        assertEquals("E", graphe.suivants("B").get(0).getDest());
        assertEquals(11, graphe.suivants("B").get(0).getCout());
    }


    /**
     * verifie que l'ajout d'arcs en double au graph ne cree pas d'enregistrements supplementaires
     */
    @Test
    public void test7_AjouterArc_duplicateArcs(){
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();

        // methode testee
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "B", 12);

        // verifications
        assertEquals(1, graphe.suivants("A").size());
    }

    /**
     * verifie si les arcs ont ete ajoutes correctement au graph
     * avec negative cout
     */
    @Test
    public void test8_AjouterArc_negativeCout(){
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "B", -12);

        // verifications
        assertEquals(1, graphe.suivants("A").size());
        assertEquals(0, graphe.suivants("A").get(0).getCout());
    }

    /**
     * verifie que la methode toString retourne la representation correcte
     */
    @Test
    public void test9_toString() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("B", "E", 11);
        graphe.ajouterArc("C", "A", 19);
        graphe.ajouterArc("D", "B", 23);
        graphe.ajouterArc("D", "C", 10);
        graphe.ajouterArc("E", "D", 43);

        String expectedOutput = "A -> B(12.0) D(87.0) \n" +
                "B -> E(11.0) \n" +
                "D -> B(23.0) C(10.0) \n" +
                "E -> D(43.0) \n" +
                "C -> A(19.0) \n";

        // verifications
        assertEquals(expectedOutput, graphe.toString());
    }


    /**
     * Test du constructeur GrapheListe avec un fichier
     */
    @Test
    public void test10_constructeur_Fichier() {
        try {
            // preparation des donnees
            GrapheListe graphe = new GrapheListe("Graphes/Graphe_exemple1.txt");

            // verifications
            assertEquals(5, graphe.listeNoeuds().size());
            assertTrue(graphe.listeNoeuds().contains("A"));
            assertTrue(graphe.listeNoeuds().contains("B"));
            assertTrue(graphe.listeNoeuds().contains("C"));
            assertTrue(graphe.listeNoeuds().contains("D"));
            assertTrue(graphe.listeNoeuds().contains("E"));

            // verifie les arcs
            assertEquals(2, graphe.suivants("A").size());
            assertEquals("B", graphe.suivants("A").get(0).getDest());
            assertEquals(12, graphe.suivants("A").get(0).getCout());
            assertEquals("D", graphe.suivants("A").get(1).getDest());
            assertEquals(87, graphe.suivants("A").get(1).getCout());

            assertEquals(1, graphe.suivants("B").size());
            assertEquals("E", graphe.suivants("B").get(0).getDest());
            assertEquals(11, graphe.suivants("B").get(0).getCout());

            assertEquals(1, graphe.suivants("C").size());
            assertEquals("A", graphe.suivants("C").get(0).getDest());
            assertEquals(19, graphe.suivants("C").get(0).getCout());

            assertEquals(2, graphe.suivants("D").size());
            assertEquals("C", graphe.suivants("D").get(0).getDest());
            assertEquals(10, graphe.suivants("D").get(0).getCout());
            assertEquals("B", graphe.suivants("D").get(1).getDest());
            assertEquals(23, graphe.suivants("D").get(1).getCout());

            assertEquals(1, graphe.suivants("E").size());
            assertEquals("D", graphe.suivants("E").get(0).getDest());
            assertEquals(43, graphe.suivants("E").get(0).getCout());

        } catch (IOException e) {
            fail("IOException n'aurait pas du etre thrown");
        }
    }

    /**
     * Test du constructeur GrapheListe avec un fichier invalide
     */
    @Test
    public void test11_constructeur_FichierInvalide() {
        // preparation des donnees
        String filename = "Graphes/Graphe_invalid.txt";

        // verifications
        assertThrows(IOException.class, () -> new GrapheListe(filename));
    }

    /**
     * Test du constructeur GrapheListe avec un fichier vide
     */
    @Test
    public void test12_constructeur_FichierVide() {
        try {
            String filename = "Graphes/Graphe_vide.txt";

            // methode testee
            GrapheListe graphe = new GrapheListe(filename);

            // verifications
            assertTrue(graphe.listeNoeuds().isEmpty());

        } catch (IOException e) {
            fail("IOException n'aurait pas du etre thrown");
        }
    }
}
