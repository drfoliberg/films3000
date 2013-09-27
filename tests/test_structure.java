package tests;

import org.drfoliberg.films3000.data.database.structure.Colonne;
import org.drfoliberg.films3000.data.database.structure.Structure;
import org.drfoliberg.films3000.data.database.structure.Table;
import org.drfoliberg.films3000.data.database.structure.constantes.Proprietes;
import org.drfoliberg.films3000.data.database.structure.types.Char;
import org.drfoliberg.films3000.data.database.structure.types.VarChar;

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
