package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public final class GestionCartes {

	private static final Random GENERATEUR = new Random();

	private GestionCartes() {
		throw new IllegalStateException("Utility class");
	}

	public static <E> E extraire(List<E> liste) {
		if (liste == null || liste.isEmpty()) {
			throw new IllegalArgumentException("La liste ne doit pas être vide.");
		}
		int indiceAleatoire = GENERATEUR.nextInt(liste.size());
		return liste.remove(indiceAleatoire);
	}

	public static <E> E extraireIterator(List<E> liste) {
		if (liste == null || liste.isEmpty()) {
			throw new IllegalArgumentException("La liste ne doit pas être vide.");
		}
		int indiceAleatoire = GENERATEUR.nextInt(liste.size());
		E elementChoisi = null;
		int position = 0;

		for (ListIterator<E> it = liste.listIterator(); it.hasNext(); position++) {
			E element = it.next();
			if (position == indiceAleatoire) {
				elementChoisi = element;
				it.remove();
				break;
			}
		}
		return elementChoisi;
	}

	public static <E> List<E> melanger(List<E> liste) {
		List<E> resultat = new ArrayList<>();
		while (!liste.isEmpty()) {
			resultat.add(extraire(liste));
		}
		return resultat;
	}

	public static <E> boolean verifierMelange(List<E> liste1, List<E> liste2) {
		if (liste1.size() != liste2.size())
			return false;
		for (E elem : liste1) {
			if (Collections.frequency(liste1, elem) != Collections.frequency(liste2, elem)) {
				return false;
			}
		}
		return true;
	}

	public static <E> List<E> rassembler(List<E> liste) {
		List<E> resultat = new ArrayList<>();
		for (E elem : liste) {
			if (!resultat.contains(elem)) {
				ajouterElementsEgaux(liste, resultat, elem);
			}
		}
		return resultat;
	}

	private static <E> void ajouterElementsEgaux(List<E> source, List<E> destination, E valeur) {
		for (E elem : source) {
			if (elem.equals(valeur)) {
				destination.add(elem);
			}
		}
	}

	public static <E> List<E> rassemblerV2(List<E> liste) {
		return rassembler(liste);
	}

	public static <E> boolean verifierRassemblement(List<E> liste) {
		if (liste.isEmpty())
			return true;

		E valeurPrecedente = null;
		for (ListIterator<E> it1 = liste.listIterator(); it1.hasNext();) {
			E valeurActuelle = it1.next();
			if (valeurPrecedente != null && !valeurActuelle.equals(valeurPrecedente)) {
				for (ListIterator<E> it2 = liste.listIterator(it1.previousIndex()); it2.hasNext();) {
					E valeurSuivante = it2.next();
					if (valeurSuivante.equals(valeurPrecedente)) {
						return false;
					}
				}
			}
			valeurPrecedente = valeurActuelle;
		}
		return true;
	}
}