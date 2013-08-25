package gestionDonnees.donnees.baseDonnees.structure;

import gestionDonnees.donnees.baseDonnees.structure.constantes.Proprietes;
import gestionDonnees.donnees.baseDonnees.structure.types.Char;
import gestionDonnees.donnees.baseDonnees.structure.types.VarChar;

public class test_structure {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Structure structure = new Structure();
		Table films = new Table("films");
		films.ajouterColonnes(new Colonne[] {
				new Colonne("id", new Char(43), (new Proprietes[] { Proprietes.AI, Proprietes.UNSIGNED, Proprietes.NOT_NULL }), true),
				new Colonne("titre", new VarChar(30), (new Proprietes[] { Proprietes.NULL }), false) });
		structure.ajouterTable(films);
		System.out.println(structure.getSql());
	}

}
