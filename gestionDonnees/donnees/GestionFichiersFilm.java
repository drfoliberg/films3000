package gestionDonnees.donnees;

import java.io.File;
import java.util.ArrayList;

import composants.fichiers.FichierFilm;

public interface GestionFichiersFilm {
	public ArrayList<FichierFilm> getFichiers();

	public void supprimerFichierFilm(int idFichier);

	public ArrayList<FichierFilm> getFichierFilm(File fichier);

	public void supprimerFichierFilm(FichierFilm fichier);

	public void ajouterFichierFilm(FichierFilm fichier);
}
