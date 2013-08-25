package gestionDonnees.donnees.baseDonnees.structure;

import java.io.Serializable;
import java.util.ArrayList;

public class Table implements Serializable {

	private static final long serialVersionUID = -5093766785963838587L;
	private ArrayList<Colonne> colonnes;
	private ArrayList<Colonne> primary_key;
	private String nomTable;

	public Table(String nomTable) {
		colonnes = new ArrayList<>();
		primary_key = new ArrayList<>();
		this.nomTable = nomTable;
	}

	public void ajouterColonne(Colonne colonne) {
		if (colonne.estPrimaire()) {
			this.primary_key.add(colonne);
		}
		this.colonnes.add(colonne);
	}

	public void ajouterColonnes(Colonne[] colonnes) {
		for (Colonne colonne : colonnes) {
			ajouterColonne(colonne);
		}
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
				sql.append(" " + colonne.getNom());
				if (i++ != 0 && primary_key.size() != 0) {
					sql.append(",");
				}
			}
			sql.append(")");
		}

		sql.append("\n);");

		return sql;
	}

	@Override
	public boolean equals(Object o) {
		boolean egal = false;
		if (o instanceof Table) {
			Table compare = (Table) o;
			if (this.colonnes.size() == compare.getColonnes().size() && this.nomTable.equals(compare.nomTable)
					&& this.primary_key.size() == compare.primary_key.size()
					&& this.colonnes.containsAll(compare.colonnes) && this.primary_key.containsAll(compare.primary_key)) {
				egal = true;
			}
		}

		return egal;

	}

	public ArrayList<Colonne> getColonnes() {
		return colonnes;
	}

	public ArrayList<Colonne> getPrimary_key() {
		return primary_key;
	}

	public String getNomTable() {
		return nomTable;
	}
}
