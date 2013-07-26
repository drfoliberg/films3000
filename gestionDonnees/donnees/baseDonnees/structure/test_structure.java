package gestionDonnees.donnees.baseDonnees.structure;

import gestionDonnees.donnees.baseDonnees.h2.H2;

import java.util.ArrayList;

public class test_structure {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		H2 h2 = new H2();
		h2.setNom("films3000");
		h2.creerBaseDonnees();
	}

}
