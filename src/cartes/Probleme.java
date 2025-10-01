package cartes;

public abstract class Probleme extends Carte {
	protected final Type type;

	protected Probleme(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		Probleme other = (Probleme) obj;
		return type == other.type;
	}
}
