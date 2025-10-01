package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte> {

    private final Carte[] cartes;
    private int nbCartes;
    private int modCount = 0;

    public Sabot(Carte[] source) {
        this.cartes = new Carte[source.length];
        System.arraycopy(source, 0, this.cartes, 0, source.length);
        this.nbCartes = source.length;
    }

    public boolean estVide() {
        return nbCartes == 0;
    }

    public void ajouterCarte(Carte c) {
        if (nbCartes >= cartes.length) {
            throw new IllegalStateException("Capacité du sabot dépassée");
        }
        cartes[nbCartes++] = c;
        modCount++;
    }

    @Override
    public Iterator<Carte> iterator() {
        return new Iterator<Carte>() {
            int cursor = 0;
            int lastRet = -1;
            int expectedModCount = modCount;

            private void checkForComod() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                return cursor < nbCartes;
            }

            @Override
            public Carte next() {
                checkForComod();
                if (!hasNext()) throw new NoSuchElementException();
                lastRet = cursor;
                return cartes[cursor++];
            }

            @Override
            public void remove() {
                checkForComod();
                if (lastRet < 0) throw new IllegalStateException("remove() sans next()");
                int numMoved = nbCartes - lastRet - 1;
                if (numMoved > 0) {
                    System.arraycopy(cartes, lastRet + 1, cartes, lastRet, numMoved);
                }
                cartes[--nbCartes] = null;
                cursor = lastRet;
                lastRet = -1;
                modCount++;
                expectedModCount = modCount;
            }
        };
    }

    public Carte piocher() {
        Iterator<Carte> it = iterator();
        if (!it.hasNext()) throw new NoSuchElementException("Sabot vide");
        Carte c = it.next();
        it.remove();
        return c;
    }
}