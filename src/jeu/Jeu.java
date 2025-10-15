package jeu;

import java.util.Arrays;
import cartes.JeuDeCartes;
import cartes.Carte;
import java.util.List;
import java.util.Collections;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;

	protected Jeu() {
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		Carte[] cartes = jeuDeCartes.donnerCartes();

		List<Carte> listeCartes = Arrays.asList(cartes);
		Collections.addAll(listeCartes, cartes);
		List<Carte> cartesMelangees = GestionCartes.melanger(listeCartes);
		Carte[] tableauMelangee = cartesMelangees.toArray(new Carte[0]);
		sabot = new Sabot(tableauMelangee);
	}

	protected Sabot getSabot() {
		return sabot;

	}

}
