package testsFonctionnels;

import java.util.Iterator;

import cartes.Botte;
import cartes.Carte;
import cartes.JeuDeCartes;
import cartes.Type;
import jeu.Sabot;

public class TestSabot {

    private final JeuDeCartes jeu = new JeuDeCartes();
    private Sabot sabot = new Sabot(jeu.donnerCartes());

    // 4.2.a : utiliser piocher jusqu'à ce que le sabot soit vide
    public void questionA() {
        while (!sabot.estVide()) {
            Carte carte = sabot.piocher();
            System.out.println("je pioche " + carte);
        }
    }

    // 4.2.b : même résultat avec itérateur + remove()
    public void questionB() {
        sabot = new Sabot(jeu.donnerCartes());
        for (Iterator<Carte> it = sabot.iterator(); it.hasNext();) {
            Carte carte = it.next();
            System.out.println("je pioche " + carte);
            it.remove();
        }
    }

    // 4.2.c : provoquer des exceptions
    public void questionC() {
        // Cas 1 : appel à piocher dans une boucle d'itération
        sabot = new Sabot(jeu.donnerCartes());
        try {
            for (Carte c : sabot) {
                System.out.println("je pioche " + c);
                sabot.piocher(); // modification concurrente
                break;
            }
        } catch (Exception e) {
            System.out.println("Exception attendue : " + e);
        }

        // Cas 2 : après avoir pioché une carte, insérer As du volant pendant l'itération
        sabot = new Sabot(jeu.donnerCartes());
        sabot.piocher(); // libère une place
        try {
            for (Carte c : sabot) {
                sabot.ajouterCarte(new Botte(Type.ACCIDENT)); // insertion concurrente
                break;
            }
        } catch (Exception e) {
            System.out.println("Exception attendue : " + e);
        }
    }

    public static void main(String[] args) {
        TestSabot test = new TestSabot();
        System.out.println("===== Question A =====");
        test.questionA();
        System.out.println("===== Question B =====");
        test.questionB();
        System.out.println("===== Question C =====");
        test.questionC();
    }
}