package gestionDonnees.donnees.baseDonnees.structure;

import java.util.ArrayList;

public class test_structure {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Structure struct = new Structure();
		Colonne i = new Colonne("verger", "INT", new String[] { "NOT NULL" });
		Colonne j = new Colonne("verger1", "INT", new String[] { "NOT NULL" });
		Colonne k = new Colonne("verger423", "INT", new String[] { "NULL" });
		Colonne m = new Colonne("chien", "TEXT", new String[] { "NOT NULL" });
		ArrayList<Colonne> colonnes1 = new ArrayList<>();
		ArrayList<Colonne> colonnes1_p = new ArrayList<>();
		ArrayList<Colonne> colonnes2 = new ArrayList<>();
		ArrayList<Colonne> colonnes2_p = new ArrayList<>();
		colonnes1.add(i);
		colonnes1.add(j);
		colonnes1_p.add(i);

		colonnes2.add(k);
		colonnes2.add(m);
		colonnes2_p.add(k);

		Table table = new Table(colonnes1, colonnes1_p, "nom_table");
		Table table2 = new Table(colonnes2, colonnes2_p, "nom_table2");

		struct.ajouterTable(table2);
		struct.ajouterTable(table);

		System.out.println(struct.getSql());
	}

}
