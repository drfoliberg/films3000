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

	public Table(String nomTable) {
		colonnes = new ArrayList<>();
		primary_key = new ArrayList<>();
		this.nomTable = nomTable;
	}

	public void ajouterClefPrimaire(Colonne colonne) {
		this.primary_key.add(colonne);
	}

	public void ajouterColonne(Colonne colonne) {
		this.colonnes.add(colonne);
	}

	public void ajouterColonnes(Colonne[] colonnes) {
		for (Colonne colonne : colonnes) {
			this.colonnes.add(colonne);
		}
	}

	public StringBuilder getSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE " + this.nomTable + " (\n ");
		for (Colonne colonne : colonnes) {
			sql.append(colonne.getSql() + "\n ");
			if (colonne.estPrimaire() && !primary_key.contains(colonne)) {
				primary_key.add(colonne);
			}
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

		sql.append("\n);");

		return sql;
	}
}
