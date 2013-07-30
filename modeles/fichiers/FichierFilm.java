package modeles.fichiers;

import java.io.File;
import java.util.ArrayList;

import modeles.fichiers.stream.Stream;
import outils.Renomeur;

/**
 * 
 * @author justin
 */
public class FichierFilm {

	private int id_fichier;
	public int id_tmdb;
	public int duree;
	public long taille;

	public String format;
	public String nomFichier;
	public File fichierDisque;
	public ArrayList<Stream> streams;
	public String titre;
	public int annee;

	public FichierFilm(File fichier) {
		this.fichierDisque = fichier;
		nomFichier = fichier.getPath().replaceAll("\\\\", "/");
	}

	public boolean analyserTitreDate() {
		Renomeur r = new Renomeur(fichierDisque);
		titre = r.getTitre();
		annee = r.getAnnee();
		return r.dateEstValide();
	}

	public void setStreams(ArrayList<Stream> streams) {
		this.streams = streams;
	}

	public int getId_fichier() {
		return id_fichier;
	}

	public String getTitre() {
		return this.titre;
	}

}