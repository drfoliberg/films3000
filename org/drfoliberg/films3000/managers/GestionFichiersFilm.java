package org.drfoliberg.films3000.managers;

import java.io.File;
import java.util.ArrayList;

import org.drfoliberg.films3000.models.file.MovieFile;



public interface GestionFichiersFilm {
	public ArrayList<MovieFile> getFichiers();

	public void supprimerFichierFilm(int idFichier);

	public ArrayList<MovieFile> getFichierFilm(File fichier);

	public void supprimerFichierFilm(MovieFile fichier);

	public void ajouterFichierFilm(MovieFile fichier);
}
