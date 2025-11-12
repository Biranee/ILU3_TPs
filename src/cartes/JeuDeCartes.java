package cartes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JeuDeCartes {

	private Configuration[] typesDeCartes = new Configuration[] { new Configuration(new Borne(25), 10),
			new Configuration(new Borne(50), 10), new Configuration(new Borne(75), 10),
			new Configuration(new Borne(100), 12), new Configuration(new Borne(200), 4),

			new Configuration(new Parade(Type.FEU), 14), new Configuration(new FinLimite(), 6),
			new Configuration(new Parade(Type.ESSENCE), 6), new Configuration(new Parade(Type.CREVAISON), 6),
			new Configuration(new Parade(Type.ACCIDENT), 6),

			new Configuration(new Attaque(Type.FEU), 5), new Configuration(new DebutLimite(), 4),
			new Configuration(new Attaque(Type.ESSENCE), 3), new Configuration(new Attaque(Type.CREVAISON), 3),
			new Configuration(new Attaque(Type.ACCIDENT), 3),

			new Configuration(new Botte(Type.FEU), 1), new Configuration(new Botte(Type.ESSENCE), 1),
			new Configuration(new Botte(Type.CREVAISON), 1), new Configuration(new Botte(Type.ACCIDENT), 1) };

	private static class Configuration {
		private final Carte carte;
		private final int nbExemplaires;

		private Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}

		private Carte getCarte() {
			return carte;
		}

		private int getNbExemplaires() {
			return nbExemplaires;
		}
	}

	public String affichageJeuDeCartes() {
		StringBuilder sb = new StringBuilder();
		sb.append("JEU :\n\n");
		for (Configuration configuration : typesDeCartes) {
			sb.append(configuration.getNbExemplaires());
			sb.append(' ');
			sb.append(configuration.getCarte().toString());
			sb.append('\n');
		}
		return sb.toString();
	}

	public Carte[] donnerCartes() {
		int total = 0;
		for (Configuration configuration : typesDeCartes) {
			total += configuration.getNbExemplaires();
		}

		Carte[] toutesLesCartes = new Carte[total];
		int index = 0;

		for (Configuration configuration : typesDeCartes) {
			int quantite = configuration.getNbExemplaires();
			Carte modele = configuration.getCarte();
			for (int j = 0; j < quantite; j++) { 
				toutesLesCartes[index++] = modele;
			}
		}
		return toutesLesCartes;
	}

	public boolean checkCount() {
		Carte[] toutesLesCartes = donnerCartes();

		int totalAttendu = 0;
		for (Configuration configuration : typesDeCartes) {
			totalAttendu += configuration.getNbExemplaires();
		}
		if (toutesLesCartes.length != totalAttendu) {
			return false;
		}

		List<Carte> listeCartes = Arrays.asList(toutesLesCartes);
		for (Configuration configuration : typesDeCartes) {
			int effectifObserve = Collections.frequency(listeCartes, configuration.getCarte());
			if (effectifObserve != configuration.getNbExemplaires()) {
				return false;
			}
		}
		return true;
	}
}