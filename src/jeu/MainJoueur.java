package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte> {

	private List<Carte> cartesEnMain;

	public MainJoueur() {
		cartesEnMain = new ArrayList<>();
	}

	public void prendre(Carte carte) {
		cartesEnMain.add(carte);
	}

	public void jouer(Carte carte) {
		if (!cartesEnMain.contains(carte)) {
			throw new IllegalArgumentException("La carte doit être présente dans la main pour être jouée.");
		}
		cartesEnMain.remove(carte);
	}

	@Override
	public String toString() {
		return "Main : " + cartesEnMain;
	}

	@Override
	public Iterator<Carte> iterator() {
		return cartesEnMain.iterator();
	}
}