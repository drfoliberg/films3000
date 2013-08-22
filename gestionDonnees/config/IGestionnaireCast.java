package gestionDonnees.config;

import java.io.File;

import modeles.films.personne.InfoFilm;
import modeles.films.personne.Personne;
import modeles.films.personne.Role;

public interface IGestionnaireCast {

	public boolean garder(InfoFilm info);
	
	public boolean garder(Role role);

	public boolean ouvrirListe(File fichier);

	public boolean enregisterListe(File fichier);
	
	public boolean ajouterRole(Role role);

	public boolean retirer(Role role);

	public void getDefault();

}
