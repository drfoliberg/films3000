package tests;

import org.drfoliberg.films3000.gestionDonnees.config.Config;
import org.drfoliberg.films3000.gestionDonnees.scrappers.web.Tmdb;
import org.drfoliberg.films3000.modeles.films.Film;


import com.omertron.themoviedbapi.MovieDbException;

public class testTmdb {

	/**
	 * @param args
	 * @throws MovieDbException 
	 */
	public static void main(String[] args) throws MovieDbException {
		Tmdb f =  new Tmdb(new Config("20d2c5786283461e15f82c94a98cca17", "en", true, true, "",new String [0]));
		Film film = f.getFilm(694);
		System.out.println();
	}

}
