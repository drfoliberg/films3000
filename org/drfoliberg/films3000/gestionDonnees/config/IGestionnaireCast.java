package org.drfoliberg.films3000.gestionDonnees.config;

import java.io.File;

import org.drfoliberg.films3000.modeles.films.personne.InfoFilm;
import org.drfoliberg.films3000.modeles.films.personne.Role;


public interface IGestionnaireCast {

	public boolean garder(InfoFilm info);
	
	public boolean garder(Role role);

	public boolean ouvrirListe(File fichier);

	public boolean enregisterListe(File fichier);
	
	public boolean ajouterRole(Role role);

	public boolean retirer(Role role);

	public void getDefault();

}
