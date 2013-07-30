package modeles.fichiers;

import java.io.File;
import java.util.ArrayList;

import modeles.fichiers.stream.Stream;

/**
 * 
 * @author justin
 */
public class FichierFilm extends Fichier {

	private String titre;
	private int annee;

	public FichierFilm(int id_fichier, int id_api, int duree_fichier, long taille, String format, String nomFichier,
			File fichierDisque, ArrayList<Stream> streams) {
		super(id_fichier, id_api, duree_fichier, taille, format, nomFichier, fichierDisque, streams);

	}

	public FichierFilm(int id_fichier, int id_api, File fichierDisque) {
		super(id_fichier, id_api, fichierDisque);
		RenomeurFilm r = new RenomeurFilm(getFichierDisque());
		titre = r.getTitre();
		annee = r.getAnnee();
	}

	public String getTitre() {
		return this.titre;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

}