package gestionDonnees.donnees.baseDonnees.structure;

import java.util.ArrayList;

public class Structure {

	private ArrayList<Table> tables;

	public Structure() {
		tables = new ArrayList<>();
	}

	public void ajouterTable(Table table) {
		this.tables.add(table);
	}

	public String getSql() {
		StringBuilder sql = new StringBuilder();
		for (Table table : tables) {
			sql.append(table.getSql());
		}

		return sql.toString();
	}
}
