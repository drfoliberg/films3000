package gestionDonnees.config;

import java.io.Serializable;

import modeles.films.personne.Personne;

public interface GestionnaireCast extends Serializable{
	
	
	public boolean garder(Personne personne);
	
	public boolean ouvrirListe();
}
