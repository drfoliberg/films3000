package gestionDonnees.donnees.baseDonnees.structure;

public class Colonne {
	String nom;
	String type;
	String[] proprietes;
	boolean primaire;

	public Colonne(String nom, String type, String[] proprietes) {
		this.nom = nom;
		this.type = type;
		this.proprietes = proprietes;
		this.primaire = false;
		for (int i = 0; i < proprietes.length; i++) {
			if (proprietes[i].equalsIgnoreCase("PRIMARY")) {
				this.primaire = true;
			}
		}
	}

	public StringBuilder getSql() {
		StringBuilder sql = new StringBuilder();
		sql.append(" " + nom + " " + type);
		for (int i = 0; i < proprietes.length; i++) {
			if (!proprietes[i].equalsIgnoreCase("PRIMARY")) {
				sql.append(" " + proprietes[i]);
			}
		}
		sql.append(",");
		return sql;
	}

	public boolean estPrimaire() {
		return this.primaire;
	}

	@Override
	public boolean equals(Object obj) {
		boolean egal = false;
		if (obj instanceof Colonne) {
			Colonne compare = (Colonne) obj;
			if (compare.getNom().equals(this.getNom())) {
				egal = true;
			}
		}
		return egal;
	}

	public String getNom() {
		return nom;
	}

	public String getType() {
		return type;
	}

	public String[] getProprietes() {
		return proprietes;
	}

	public boolean isPrimaire() {
		return primaire;
	}

}
