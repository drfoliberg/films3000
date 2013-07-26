package gestionDonnees.donnees.baseDonnees.structure;

import java.util.ArrayList;

public class Table {
	private ArrayList<Colonne> colonnes;
	private ArrayList<Colonne> primary_key;
	private String nomTable;

	public Table(ArrayList<Colonne> colonnes, ArrayList<Colonne> primary_key, String nomTable) {
		this.colonnes = colonnes;
		this.primary_key = primary_key;
		this.nomTable = nomTable;
	}

	public StringBuilder getSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE " + this.nomTable + " (\n ");
		for (Colonne colonne : colonnes) {
			sql.append(colonne.getSql() + "\n ");
		}
		if (primary_key.size() != 0) {
			int i = 0;
			sql.append("PRIMARY KEY(");
			for (Colonne colonne : primary_key) {
				sql.append(" " + colonne.nom);
				if (i++ != 0 && primary_key.size() != 0) {
					sql.append(",");
				}
			}
			sql.append(")");
		}

		sql.append(");");

		return sql;
	}
}
