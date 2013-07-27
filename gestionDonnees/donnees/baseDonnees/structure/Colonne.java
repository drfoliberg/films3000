package gestionDonnees.donnees.baseDonnees.structure;

public class Colonne {
	private String nom;
	private String type;
	private String[] proprietes;
	private boolean primaire;
	private StringBuilder sql;

	/**
	 * Constructeur alternatif qui sépare les propriétés
	 * 
	 * @param nom
	 *            Nom de la table
	 * @param type
	 *            Type de donnée
	 * @param proprietes
	 *            Propriétés séparées par des virgules. Si vide, la propriété
	 *            NULL sera attribuée.
	 * @param primaire
	 *            Fait partie de la clé primare
	 */

	public Colonne(String nom, String type, String proprietes, boolean primaire) {
		this(nom, type, proprietes.split(","), primaire);
	}

	/**
	 * Constructeur de base pour une colonne
	 * 
	 * @param nom
	 *            Nom de la table
	 * @param type
	 *            Type de donnée
	 * @param proprietes
	 *            Tableau des propriétés. Si vide, la propriété NULL sera
	 *            attribuée.
	 * @param primaire
	 *            Fait partie de la clé primare
	 */
	public Colonne(String nom, String type, String[] proprietes, boolean primaire) {
		this.nom = nom;
		this.type = type;
		if (proprietes.length == 1 && proprietes[0].equals("") || proprietes.length == 0) {
			this.proprietes = new String[] { "NULL" };
		} else {
			this.proprietes = proprietes;
		}
		this.primaire = primaire;
	}

	public StringBuilder getSql() {
		if (this.sql == null) {
			sql = new StringBuilder();
			sql.append(" " + nom + " " + type);
			for (int i = 0; i < proprietes.length; i++) {
				sql.append(" " + proprietes[i]);

			}
			sql.append(",");
		}
		return sql;
	}

	@Override
	public String toString() {
		return getSql().toString();
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

}
