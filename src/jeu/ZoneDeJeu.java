package jeu;

import java.util.LinkedList;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {

	private final List<Carte> pileLimites;
	private final List<Carte> pileBatailles;
	private final List<Carte> bornes;

	public ZoneDeJeu() {
		pileLimites = new LinkedList<>();
		pileBatailles = new LinkedList<>();
		bornes = new LinkedList<>();
	}

	public void deposer(Carte carte) {
		if (carte instanceof Borne) {
			bornes.add(carte);
		} else if (carte instanceof Limite) {
			pileLimites.add(carte);
		} else if (carte instanceof Bataille) {
			pileBatailles.add(carte);
		}
	}

	public int donnerLimitationVitesse() {
		if (pileLimites.isEmpty())
			return 200;
		Carte carteSommet = pileLimites.get(pileLimites.size() - 1);
		if (carteSommet instanceof FinLimite)
			return 200;
		if (carteSommet instanceof Limite)
			return 50;
		return 200;
	}

	public int donnerKmParcourus() {
		int totalKilometres = 0;
		for (Carte carte : bornes) {
			if (carte instanceof Borne borne) {
				totalKilometres += borne.getKm();
			}
		}
		return totalKilometres;
	}

	public boolean peutAvancer() {
		if (pileBatailles.isEmpty())
			return false;
		Carte carteSommet = pileBatailles.get(pileBatailles.size() - 1);
		return (carteSommet instanceof Parade parade) && parade.getType() == Type.FEU;
	}

	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		}
		if (carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		}
		if (carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		}
		return false;
	}

	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && borne.getKm() <= donnerLimitationVitesse()
				&& donnerKmParcourus() + borne.getKm() <= 1000;
	}

	private boolean estDepotLimiteAutorise(Limite carteLimite) {
		if (pileLimites.isEmpty()) {
			return !(carteLimite instanceof FinLimite);
		}

		Carte carteSommet = pileLimites.get(pileLimites.size() - 1);

		if (!(carteLimite instanceof FinLimite)) {
			return (carteSommet instanceof FinLimite);
		}

		return (carteSommet instanceof Limite) && !(carteSommet instanceof FinLimite);
	}

	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille == null)
			return false;

		if (bataille instanceof Attaque) {
			return estDepotAttaqueAutorise();
		}
		if (bataille instanceof Parade parade) {
			return estDepotParadeAutorise(parade);
		}
		return false;
	}

	private boolean estDepotAttaqueAutorise() {
		return peutAvancer();
	}

	private boolean estDepotParadeAutorise(Parade parade) {
		Carte carteSommet = carteSommetBatailleOuNull();

		if (parade.getType() == Type.FEU) {
			return estFeuVertAutorise(carteSommet);
		}
		return estParadeStandardAutorisee(parade, carteSommet);
	}

	private boolean estFeuVertAutorise(Carte carteSommet) {
		if (carteSommet == null)
			return true;
		if (carteSommet instanceof Attaque attaque) {
			return attaque.getType() == Type.FEU;
		}
		if (carteSommet instanceof Parade autreParade) {
			return autreParade.getType() != Type.FEU;
		}
		return false;
	}

	private boolean estParadeStandardAutorisee(Parade parade, Carte carteSommet) {
		if (carteSommet instanceof Attaque attaque) {
			return attaque.getType() == parade.getType();
		}
		return false;
	}

	private Carte carteSommetBatailleOuNull() {
		if (pileBatailles.isEmpty())
			return null;
		return pileBatailles.get(pileBatailles.size() - 1);
	}

	public List<Carte> getPileLimites() {
		return pileLimites;
	}

	public List<Carte> getPileBatailles() {
		return pileBatailles;
	}

	public List<Carte> getBornes() {
		return bornes;
	}
}