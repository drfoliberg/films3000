package org.drfoliberg.films3000.modeles.fichiers;

import java.io.File;

/**
 * 
 * @author justin
 */
public class FichierFilm extends Fichier {

	private String titre;
	private int annee;

	/**
	 * Base contructor for the FileMovie object
	 * @param fichierDisque
	 */
	public FichierFilm(File fichierDisque) {
		super(fichierDisque);
		RenomeurFilm r = new RenomeurFilm(fichierDisque);
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