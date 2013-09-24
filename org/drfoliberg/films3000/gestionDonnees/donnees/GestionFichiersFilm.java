package org.drfoliberg.films3000.gestionDonnees.donnees;

import java.io.File;
import java.util.ArrayList;

import org.drfoliberg.films3000.modeles.fichiers.FichierFilm;



public interface GestionFichiersFilm {
	public ArrayList<FichierFilm> getFichiers();

	public void supprimerFichierFilm(int idFichier);

	public ArrayList<FichierFilm> getFichierFilm(File fichier);

	public void supprimerFichierFilm(FichierFilm fichier);

	public void ajouterFichierFilm(FichierFilm fichier);
}
