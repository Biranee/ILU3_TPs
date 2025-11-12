package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte> {

	private final Carte[] cartes;
	private int nbCartes;
	private int compteurModifs = 0;

	public Sabot(Carte[] source) {
		this.cartes = new Carte[source.length];
		System.arraycopy(source, 0, this.cartes, 0, source.length);
		this.nbCartes = source.length;
	}

	public boolean estVide() {
		return nbCartes == 0;
	}

	public void ajouterCarte(Carte carte) {
		if (nbCartes >= cartes.length) {
			throw new IllegalStateException("Capacité du sabot dépassée");
		}
		cartes[nbCartes++] = carte;
		compteurModifs++;
	}

	@Override
	public Iterator<Carte> iterator() {
		return new Iterator<Carte>() {
			int curseur = 0;
			int dernierRendu = -1;
			int compteurAttendu = compteurModifs;

			private void verifierModifsConcurrentes() {
				if (compteurAttendu != compteurModifs) {
					throw new ConcurrentModificationException();
				}
			}

			@Override
			public boolean hasNext() {
				return curseur < nbCartes;
			}

			@Override
			public Carte next() {
				verifierModifsConcurrentes();
				if (!hasNext())
					throw new NoSuchElementException();
				dernierRendu = curseur;
				return cartes[curseur++];
			}

			@Override
			public void remove() {
				verifierModifsConcurrentes();
				if (dernierRendu < 0)
					throw new IllegalStateException("remove() sans next()");
				int nbDeplacements = nbCartes - dernierRendu - 1;
				if (nbDeplacements > 0) {
					System.arraycopy(cartes, dernierRendu + 1, cartes, dernierRendu, nbDeplacements);
				}
				cartes[--nbCartes] = null;
				curseur = dernierRendu;
				dernierRendu = -1;
				compteurModifs++;
				compteurAttendu = compteurModifs;
			}
		};
	}

	public Carte piocher() {
		Iterator<Carte> it = iterator();
		if (!it.hasNext())
			throw new NoSuchElementException("Sabot vide");
		Carte carte = it.next();
		it.remove();
		return carte;
	}
}