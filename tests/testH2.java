package tests;

import gestionDonnees.donnees.baseDonnees.h2.H2;

public class testH2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		H2 h2 = new H2();
//		h2.setNom("films3000");
//		h2.open();
		h2.creerBaseDonnees();

	}

}
