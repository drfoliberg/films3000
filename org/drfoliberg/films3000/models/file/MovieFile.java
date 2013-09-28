package org.drfoliberg.films3000.models.file;

import java.io.File;

/**
 * 
 * @author justin
 */
public class MovieFile extends BaseFile {

	private String titre;
	private int annee;

	/**
	 * Base constructor for the FileMovie object
	 * @param fichierDisque
	 */
	public MovieFile(File fichierDisque) {
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