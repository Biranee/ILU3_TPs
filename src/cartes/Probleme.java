package cartes;

public abstract class Probleme extends Carte{
	protected final Type type;
	protected Probleme(Type type) {this.type= type;}
	public Type getType() {return type;}
	
	
}
