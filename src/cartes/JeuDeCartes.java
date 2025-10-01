package cartes;

public class JeuDeCartes {
	private final Configuration[] typesDeCartes = new Configuration[] {
	        // Bornes
	        new Configuration(new Borne(25), 10),
	        new Configuration(new Borne(50), 10),
	        new Configuration(new Borne(75), 10),
	        new Configuration(new Borne(100), 12),
	        new Configuration(new Borne(200), 4),

	        // Parades
	        new Configuration(new Parade(Type.FEU), 14),         // Feu Vert
	        new Configuration(new FinLimite(), 6),               // Fin Limite
	        new Configuration(new Parade(Type.ESSENCE), 6),      // Bidon d'essence
	        new Configuration(new Parade(Type.CREVAISON), 6),    // Roue de secours
	        new Configuration(new Parade(Type.ACCIDENT), 6),     // Réparation

	        // Attaques
	        new Configuration(new Attaque(Type.FEU), 5),         // Feu Rouge
	        new Configuration(new DebutLimite(), 4),             // Limite 50
	        new Configuration(new Attaque(Type.ESSENCE), 3),     // Panne d'essence
	        new Configuration(new Attaque(Type.CREVAISON), 3),   // Crevaison
	        new Configuration(new Attaque(Type.ACCIDENT), 3),    // Accident

	        // Bottes
	        new Configuration(new Botte(Type.FEU), 1),           // Prioritaire
	        new Configuration(new Botte(Type.ESSENCE), 1),       // Citerne
	        new Configuration(new Botte(Type.CREVAISON), 1),     // Increvable
	        new Configuration(new Botte(Type.ACCIDENT), 1)       // As du volant
	    };
	public String affichageJeuDeCartes() {
		StringBuilder sb = new StringBuilder();
		sb.append("JEU: \n\n");
		for (Configuration cfg : typesDeCartes) {
			sb.append(cfg.getNbExemplaires())
			.append(" ")
			.append(cfg.getCarte().toString())
			.append("\n");
		}
		return sb.toString();
	}
	public boolean checkCount() {
	    int total = 0;
	    for (Configuration cfg : typesDeCartes) {
	        total += cfg.getNbExemplaires();
	    }
	    return total == 106;
	}
	//test
	public Configuration[] getTypesDeCartes() {return typesDeCartes;}

}
