package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Random;

public final class GestionCartes {

    private static final Random GENERATEUR = new Random();

    private GestionCartes() { }

    public static <T> T extraire(List<T> liste) {
        if (liste == null || liste.isEmpty()) {
            throw new IllegalArgumentException("La liste ne doit pas être vide.");
        }
        int indiceAleatoire = GENERATEUR.nextInt(liste.size());
        return liste.remove(indiceAleatoire);
    }

    public static <T> T extraireIterator(List<T> liste) {
        if (liste == null || liste.isEmpty()) {
            throw new IllegalArgumentException("La liste ne doit pas être vide.");
        }
        int indiceAleatoire = GENERATEUR.nextInt(liste.size());
        T elementChoisi = null;
        int position = 0;
        for (ListIterator<T> iterateur = liste.listIterator(); iterateur.hasNext(); position++) {
            T elementCourant = iterateur.next();
            if (position == indiceAleatoire) {
                elementChoisi = elementCourant;
                iterateur.remove();
                break;
            }
        }
        return elementChoisi;
    }

    public static <T> List<T> melanger(List<T> liste) {
        if (liste == null) {
            throw new IllegalArgumentException("La liste ne doit pas être nulle.");
        }
        List<T> listeMelangee = new ArrayList<>();
        int nombreElements = liste.size();
        for (int i = 0; i < nombreElements; i++) {
            T elementExtrait = extraire(liste);
            listeMelangee.add(elementExtrait);
        }
        return listeMelangee;
    }

  

    public static <T> boolean verifierMelange(List<T> listeA, List<T> listeB) {
        if (listeA == null || listeB == null) return false;
        if (listeA.size() != listeB.size()) return false;

        List<T> dejaVerifies = new ArrayList<>();

        for (T valeur : listeA) {
            if (dejaVerifies.contains(valeur)) continue;
            int freqA = Collections.frequency(listeA, valeur);
            int freqB = Collections.frequency(listeB, valeur);
            if (freqA != freqB) return false;
            dejaVerifies.add(valeur);
        }

        for (T valeur : listeB) {
            if (dejaVerifies.contains(valeur)) continue;
            int freqA = Collections.frequency(listeA, valeur);
            int freqB = Collections.frequency(listeB, valeur);
            if (freqA != freqB) return false;
            dejaVerifies.add(valeur);
        }

        return true;
    }

    

    public static <T> List<T> rassembler(List<T> liste) {
        if (liste == null) {
            throw new IllegalArgumentException("La liste ne doit pas être nulle.");
        }
        List<T> listeRassemblee = new ArrayList<>();
        List<T> dejaTraites = new ArrayList<>();

        for (T valeurDeReference : liste) {
            if (dejaTraites.contains(valeurDeReference)) continue;
            dejaTraites.add(valeurDeReference);
            for (T valeur : liste) {
                if (Objects.equals(valeur, valeurDeReference)) {
                    listeRassemblee.add(valeur);
                }
            }
        }
        return listeRassemblee;
    }
    public static <T> List<T> rassemblerV2(List<T> liste) {
        return rassembler(liste);
    }
    

    public static <T> boolean verifierRassemblement(List<T> liste) {
        if (liste == null) return false;
        if (liste.size() <= 1) return true;

        boolean precedenteDefinie = false;
        T valeurPrecedente = null;

        for (ListIterator<T> premier = liste.listIterator(); premier.hasNext(); ) {
            T valeurCourante = premier.next();

            if (!precedenteDefinie) {
                valeurPrecedente = valeurCourante;
                precedenteDefinie = true;
                continue;
            }

            if (!Objects.equals(valeurCourante, valeurPrecedente)) {
                for (ListIterator<T> second = liste.listIterator(premier.previousIndex()); second.hasNext(); ) {
                    T valeurSuivante = second.next();
                    if (Objects.equals(valeurSuivante, valeurPrecedente)) {
                        return false;
                    }
                }
                valeurPrecedente = valeurCourante;
            }
        }
        return true;
    }
}