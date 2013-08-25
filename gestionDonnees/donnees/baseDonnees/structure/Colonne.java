package gestionDonnees.donnees.baseDonnees.structure;

import gestionDonnees.donnees.baseDonnees.structure.constantes.Proprietes;
import gestionDonnees.donnees.baseDonnees.structure.types.Type;

import java.io.Serializable;
import java.util.ArrayList;



public class Colonne implements Serializable {

	private static final long serialVersionUID = -5852282813687663771L;
	private String nom;
	private String type;
	private ArrayList<Proprietes> proprietes;
	private boolean primaire;
	private StringBuilder sql;

	/**
	 * Constructeur alternatif
	 * 
	 * @param nom
	 *            Nom de la colonne
	 * @param type
	 *            Type de donnée
	 * @param proprietes
	 *            Tableau des propriétés. Si vide, la propriété NULL sera
	 *            attribuée.
	 * @param primaire
	 *            Fait partie de la clé primare
	 */

	public Colonne(String nom, Type type, Proprietes [] proprietes, boolean primaire) {
		
		this.proprietes = new ArrayList<>();
		this.nom = nom;
		this.type = type.toString();
		if (proprietes.length == 0) {
			this.proprietes = new ArrayList<>();
			this.proprietes.add(Proprietes.NULL);
		} else {
			for (Proprietes propriete: proprietes) {
				this.proprietes.add(propriete);
			}
		}
		this.primaire = primaire;
	}

	/**
	 * Constructeur de base pour une colonne
	 * 
	 * @param nom
	 *            Nom de la table
	 * @param type
	 *            Type de donnée
	 * @param proprietes
	 *            Liste des propriétés. Si vide, la propriété NULL sera
	 *            attribuée.
	 * @param primaire
	 *            Fait partie de la clé primare
	 */
	public Colonne(String nom, Type type, ArrayList<Proprietes> proprietes, boolean primaire) {
		
		this.nom = nom;
		this.type = type.toString();
		if (proprietes.size() == 0) {
			this.proprietes = new ArrayList<>();
			this.proprietes.add(Proprietes.NULL);
		} else {
			this.proprietes = proprietes;
		}
		this.primaire = primaire;
	}

	public StringBuilder getSql() {
		if (this.sql == null) {
			sql = new StringBuilder();
			sql.append(" " + nom + " " + type);
			for (int i = 0; i < proprietes.size(); i++) {
				sql.append(" " + proprietes.get(i).getPropriete());
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
			if (compare.getNom().equals(this.getNom()) && this.primaire == compare.primaire
					&& this.type.equals(compare.type) && this.proprietes.size() == compare.proprietes.size()) {

				if (proprietes.containsAll(this.proprietes)) {
					egal = true;
				}
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

	public ArrayList<Proprietes> getProprietes() {
		return proprietes;
	}

}
