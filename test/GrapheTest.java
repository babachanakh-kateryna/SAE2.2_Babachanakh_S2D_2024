package test;
import org.junit.jupiter.api.Test;
import src.Arc;
import src.Arcs;
import src.GrapheListe;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;

/**
 * La classe GrapheListeTest pour verifier les methodes de GrapheListe
 */
public class GrapheTest {

    /**
     * verifie que les valeurs de destination et de cout sont correctement initialisees
     * (Classe Arc)
     */
    @Test
    public void test1_ArcConstructor_OK() {
        // preparation des donnees
        Arc arc = new Arc("B", 12.5);
        // verifications
        assertEquals("B", arc.getDest());
        assertEquals(12.5, arc.getCout());
    }

    /**
     * verifie que la representation sous forme de chaine de l'arc est correcte
     * (Classe Arc)
     */
    @Test
    public void test2_ArcToString_OK() {
        // preparation des donnees
        Arc arc = new Arc("C", 15.0);
        // verifications
        assertEquals("(C,15.0)", arc.toString());
    }

    /**
     * verifie que le cout de l'arc est initialise a zero si la valeur fournie est negative
     * (Classe Arc)
     */
    @Test
    public void test3_ArcConstructor_NegativeCout() {
        // preparation des donnees
        Arc arc = new Arc("D", -10.0);
        // verifications
        assertEquals(0.0, arc.getCout());
    }

    /**
     * verifie que la representation sous forme de chaine de la liste d'arcs est correcte
     * (Classe Arcs)
     */
    @Test
    public void test4_ArcsToString_OK() {
        // preparation des donnees
        Arcs arcs = new Arcs();
        arcs.ajouterArc(new Arc("B", 12.5));
        arcs.ajouterArc(new Arc("C", 15.0));

        // verifications
        assertEquals("B(12.5) C(15.0) ", arcs.toString());
    }

    /**
     * verifie si les arcs ont ete ajoutes correctement a la liste des arcs
     * (Classe Arcs)
     */
    @Test
    public void test5_ArcsAjouterArc_OK() {
        // preparation des donnees
        Arcs arcs = new Arcs();
        Arc arc = new Arc("B", 12.5);

        // methode testee
        arcs.ajouterArc(arc);
        List<Arc> arcList = arcs.getArcs();

        // verifications
        assertEquals(1, arcList.size());
        assertEquals("B", arcList.get(0).getDest());
        assertEquals(12.5, arcList.get(0).getCout());
    }

    /**
     * verifie que tous les arcs sont ajoutes correctement et sont retournes dans le bon ordre
     * (Classe Arcs)
     */
    @Test
    public void test6_ArcsAjouterArc_MultipleArcs() {
        // preparation des donnees
        Arcs arcs = new Arcs();

        // methode testee
        arcs.ajouterArc(new Arc("B", 12.5));
        arcs.ajouterArc(new Arc("C", 15.0));
        arcs.ajouterArc(new Arc("D", 20.0));

        List<Arc> arcList = arcs.getArcs();

        // verifications
        assertEquals(3, arcList.size());
        assertEquals("B", arcList.get(0).getDest());
        assertEquals(12.5, arcList.get(0).getCout());
        assertEquals("C", arcList.get(1).getDest());
        assertEquals(15.0, arcList.get(1).getCout());
        assertEquals("D", arcList.get(2).getDest());
        assertEquals(20.0, arcList.get(2).getCout());
    }

    /**
     * verifie que la liste retournee est vide pour un nouvel objet Arcs
     * (Classe Arcs)
     */
    @Test
    public void test7_ArcsConstructeur_VideArcsList() {
        // preparation des donnees
        Arcs arcs = new Arcs();

        // verifications
        assertTrue(arcs.getArcs().isEmpty());
    }

    /**
     * verifie le constructeur de graphe vide
     * (Classe GrapheListe)
     */
    @Test
    public void test8_GrapheListeConstructeur_VideGraph() {
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        // verifications
        assertTrue(graphe.listeNoeuds().isEmpty());
    }

    /**
     * verifie que la methode getIndice renvoie correctement les indices de nœuds
     * (Classe GrapheListe)
     */
    @Test
    public void test9_GrapheListegetIndice_OK() {
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
     * (Classe GrapheListe)
     *
     */
    @Test
    public void test10_GrapheListelisteNoeuds_True() {
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
     * (Classe GrapheListe)
     *
     */
    @Test
    public void test11_GrapheListelisteNoeuds_False() {
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
     * (Classe GrapheListe)
     *
     */
    @Test
    public void test12_GrapheListeSuivants_OK() {
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
     * (Classe GrapheListe)
     *
     */
    @Test
    public void test13_GrapheListeAjouterArc_OK(){
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
     * (Classe GrapheListe)
     *
     */
    @Test
    public void test14_GrapheListeAjouterArc_duplicateArcs(){
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
     * (Classe GrapheListe)
     *
     */
    @Test
    public void test15_GrapheListeAjouterArc_negativeCout(){
        // preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "B", -12);

        // verifications
        assertEquals(1, graphe.suivants("A").size());
        assertEquals(0, graphe.suivants("A").get(0).getCout());
    }

    /**
     * verifie que la methode toString retourne la representation correcte
     * (Classe GrapheListe)
     *
     */
    @Test
    public void test16_GrapheListetoString_OK() {
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
     * (Classe GrapheListe)
     *
     */
    @Test
    public void test17_GrapheListeConstructeur_Fichier() {
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
     * (Classe GrapheListe)
     *
     */
    @Test
    public void test18_GrapheListeConstructeur_FichierInvalide() {
        // preparation des donnees
        String filename = "Graphes/Graphe_invalid.txt";

        // verifications
        assertThrows(IOException.class, () -> new GrapheListe(filename));
    }

    /**
     * Test du constructeur GrapheListe avec un fichier vide
     * (Classe GrapheListe)
     *
     */
    @Test
    public void test19_GrapheListeConstructeur_FichierVide() {
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
