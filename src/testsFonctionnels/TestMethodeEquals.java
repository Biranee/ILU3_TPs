package testsFonctionnels;

import cartes.*;

public class TestMethodeEquals {
	public static void main(String[] args) {
		// a. Deux bornes 25km
		Carte b1 = new Borne(25);
		Carte b2 = new Borne(25);
		System.out.println("Deux cartes de 25km sont identiques ? " + b1.equals(b2));

		// b. Deux feux rouges (attaque de type FEU)
		Carte f1 = new Attaque(Type.FEU);
		Carte f2 = new Attaque(Type.FEU);
		System.out.println("Deux cartes de feux rouge sont identiques ? " + f1.equals(f2));

		// c. Un feu rouge et un feu vert (attaque vs parade du même type FEU)
		Carte rouge = new Attaque(Type.FEU);
		Carte vert = new Parade(Type.FEU);
		System.out.println("La carte feu rouge et la carte feu vert sont identiques ? " + rouge.equals(vert));
	}
}