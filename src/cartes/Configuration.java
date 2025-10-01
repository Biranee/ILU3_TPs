package cartes;

public class Configuration {
	private final Carte carte;
	private final int nbExemplaires;
	public Configuration(Carte carte,int nbExemplaires) {
		this.carte=carte;
		this.nbExemplaires=nbExemplaires;
	}
	public Carte getCarte() {return carte;}
	public int getNbExemplaires() {return nbExemplaires;}
}
