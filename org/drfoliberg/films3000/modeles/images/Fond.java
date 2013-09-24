package org.drfoliberg.films3000.modeles.images;


public class Fond extends Image{

	int idFilm;
	public static final int TYPE = 1;
	
	public Fond(String chemin, int idFilm) {
		super(chemin);
		this.idFilm = idFilm;
	}
	
	public int getIdFilm() {
		return idFilm;
	}
	
}
