package cartes;

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
		for (Configuration cfg : typesDeCartes)
			total += cfg.getNbExemplaires();
		Carte[] toutes = new Carte[total];
		for (int i = 0, k = 0; i < typesDeCartes.length; i++) {
			Configuration cfg = typesDeCartes[i];
			for (int j = 0; i < cfg.getNbExemplaires(); j++) {
				toutes[k++] = cfg.getCarte();
			}
		}
		return toutes;
	}

	public boolean checkCount() {
		Carte[] toutes = donnerCartes();
		int attenduTotal = 0;
		for (Configuration cfg : typesDeCartes)
			attenduTotal += cfg.getNbExemplaires();
		if (toutes.length != attenduTotal)
			return false;
		for (Configuration cfg : typesDeCartes) {
			int vus = 0;
			for (Carte c : toutes)
				if (c.equals(cfg.getCarte()))
					vus++;
			if (vus != cfg.getNbExemplaires())
				return false;
		}
		return true;
	}
}