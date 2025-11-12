package jeu;

import cartes.Carte;

public class Joueur {

	private final String nom;
	private final ZoneDeJeu zone;
	private final MainJoueur main;

	public Joueur(String nom) {
		this.nom = nom;
		this.zone = new ZoneDeJeu();
		this.main = new MainJoueur();
	}

	public String getNom() {
		return nom;
	}

	public MainJoueur getMain() {
		return main;
	}

	public ZoneDeJeu getZone() {
		return zone;
	}

	public void deposer(Carte carte) {
		zone.deposer(carte);
	}
	
	public boolean estDepotAutorise(Carte carte) {
	    return zone.estDepotAutorise(carte);
	}

	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide())
			return null;
		Carte cartePiochee = sabot.piocher();
		main.prendre(cartePiochee);
		return cartePiochee;
	}

	public int donnerKmParcourus() {
		return zone.donnerKmParcourus();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Joueur))
			return false;
		Joueur autre = (Joueur) obj;
		return nom.equals(autre.nom);
	}

	@Override
	public String toString() {
		return nom;
	}
}