package cartes;

public class Borne extends Carte {
	private final int km;

	public Borne(int km) {
		this.km = km;
	}

	public int getKm() {
		return km;
	}

	@Override
	public String toString() {
		return km + "KM";
	}
	@Override 
	public boolean equals (Object obj) {
		if (!super.equals(obj)) return false;
		Borne other = (Borne) obj;
		return km == other.km;
	}
}