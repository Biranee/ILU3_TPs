package cartes;

public enum Type {
    FEU("Feu Rouge", "Feu Vert", "Prioritaire"),
    ESSENCE("Panne d'essence", "Bidon d'essence", "Citerne"),
    CREVAISON("Crevaison", "Roue de secours", "Increvable"),
    ACCIDENT("Accident", "Réparation", "As du volant");

	private final String attaque;
	private final String parade;
	private final String botte;

	Type(String attaque, String parade, String botte) {
	    this.attaque = attaque;
	    this.parade = parade;
	    this.botte = botte;
	}
    public String nomAttaque() { return attaque; }
    public String nomParade()  { return parade; }
    public String nomBotte()   { return botte; }
}