package org.drfoliberg.films3000.models.image;


public class Backdrop extends Image{

	int movieId;
	public static final int TYPE = 1;
	
	public Backdrop(String path, int movieId) {
		super(path);
		this.movieId = movieId;
	}
	
	public int getMovieId() {
		return movieId;
	}
	
}
