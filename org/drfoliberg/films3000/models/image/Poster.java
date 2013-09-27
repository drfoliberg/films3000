package org.drfoliberg.films3000.models.image;

public class Poster extends Image {
	
	private int idFilm;
	private String langue;
	public static final int TYPE = 0;

	public Poster(String chemin, int idFilm, String langue) {
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
