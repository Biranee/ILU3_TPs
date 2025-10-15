package testsFonctionnels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class TestGestionCartes {

	private static final String MSG_RASSEMBLEE = "Rassemblée ? ";

	public static <T> boolean memesOccurrences(List<T> listeAvant, List<T> listeApres) {
		if (listeAvant == null || listeApres == null)
			return false;
		if (listeAvant.size() != listeApres.size())
			return false;

		List<T> dejaVerifies = new ArrayList<>();

		for (T valeur : listeAvant) {
			if (dejaVerifies.contains(valeur))
				continue;
			int f1 = Collections.frequency(listeAvant, valeur);
			int f2 = Collections.frequency(listeApres, valeur);
			if (f1 != f2)
				return false;
			dejaVerifies.add(valeur);
		}
		for (T valeur : listeApres) {
			if (dejaVerifies.contains(valeur))
				continue;
			int f1 = Collections.frequency(listeAvant, valeur);
			int f2 = Collections.frequency(listeApres, valeur);
			if (f1 != f2)
				return false;
			dejaVerifies.add(valeur);
		}
		return true;
	}

	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = new LinkedList<>(Arrays.asList(jeu.donnerCartes()));

		List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
		System.out.println(listeCartes);

		listeCartes = GestionCartes.melanger(listeCartes); // vide la liste passée en arg.
		System.out.println(listeCartes);

		System.out.println("liste mélangée sans erreur ? " + memesOccurrences(listeCarteNonMelangee, listeCartes));

		listeCartes = GestionCartes.rassemblerV2(listeCartes);
		System.out.println(listeCartes);
		System.out.println("liste rassemblée sans erreur ? " + GestionCartes.verifierRassemblement(listeCartes));

		List<Integer> l0 = new ArrayList<>(); // []
		List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 1, 2, 1, 3));
		List<Integer> l2 = new ArrayList<>(Arrays.asList(1, 4, 3, 2));
		List<Integer> l3 = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 1));

		System.out.println("[] -> " + GestionCartes.verifierRassemblement(l0));

		System.out.println("[1,1,2,1,3] avant  : " + l1);
		List<Integer> l1R = GestionCartes.rassembler(l1);
		System.out.println("[1,1,2,1,3] après  : " + l1R);
		System.out.println(MSG_RASSEMBLEE + GestionCartes.verifierRassemblement(l1R));

		System.out.println("[1,4,3,2] avant   : " + l2);
		List<Integer> l2R = GestionCartes.rassembler(l2);
		System.out.println("[1,4,3,2] après   : " + l2R);
		System.out.println(MSG_RASSEMBLEE + GestionCartes.verifierRassemblement(l2R));

		System.out.println("[1,1,2,3,1] avant : " + l3);
		List<Integer> l3R = GestionCartes.rassembler(l3);
		System.out.println("[1,1,2,3,1] après : " + l3R);
		System.out.println(MSG_RASSEMBLEE + GestionCartes.verifierRassemblement(l3R));
	}
}