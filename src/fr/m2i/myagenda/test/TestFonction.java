package fr.m2i.myagenda.test;

public class TestFonction {

	public static void main(String[] args) {

		int choixUtilisateur = returnValue();
		if (choixUtilisateur == 1) {
			System.out.println("Coucou Rémi");
		} else {
			System.out.println("Te connais pas toi !");
		}

	}

	public static int returnValue() {
		return 1;
	}

}
