package gestionDonnees.donnees.baseDonnees.structure;

import java.util.ArrayList;

public class Structure {

	private ArrayList<Table> tables;
	
	public String getSql(){
		StringBuilder sql = new StringBuilder();
		for (Table  table : tables) {
			sql.append(table.getSql());
		}
		
		return sql.toString();
	}
}
