package gestionDonnees.donnees.baseDonnees.structure;

import com.mysql.jdbc.PreparedStatement;

public class Colonne {
	String nom;
	String type;
	String[] proprietes;

	public Colonne(String nom, String type, String[] proprietes) {
		this.nom = nom;
		this.type = type;
		this.proprietes = proprietes;
	}

	public StringBuilder getSql() {
		StringBuilder sql = new StringBuilder();
		sql.append(" " + nom + " " + type);
		for (int i = 0; i < proprietes.length; i++) {
			sql.append(" "+proprietes[i]);
		}
		sql.append(",");
		return sql;
	}

}
