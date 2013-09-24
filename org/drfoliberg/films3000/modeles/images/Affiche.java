package org.drfoliberg.films3000.modeles.images;

public class Affiche extends Image {
	
	private int idFilm;
	private String langue;
	public static final int TYPE = 0;

	public Affiche(String chemin, int idFilm, String langue) {
		super(chemin);
		this.idFilm = idFilm;
		this.langue = langue;
	}

	public int getIdFilm() {
		return idFilm;
	}

	public String getLangue() {
		return langue;
	}

}
