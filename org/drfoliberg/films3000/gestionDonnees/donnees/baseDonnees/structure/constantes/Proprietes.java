package org.drfoliberg.films3000.gestionDonnees.donnees.baseDonnees.structure.constantes;

public enum Proprietes {
	
	UNSIGNED("UNSIGNED"),
	NULL("NULL"),
	NOT_NULL("NOT NULL"),
	AI("AUTO_INCREMENT");
	
	String propriete;
	
	private Proprietes(String propriete) {
		this.propriete = propriete;
	}
	
	public String getPropriete(){
		return this.propriete;
	}
}
