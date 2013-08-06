package tests;

import modeles.films.Film;
import gestionDonnees.config.Config;
import gestionDonnees.scrappers.web.Tmdb;

import com.omertron.themoviedbapi.MovieDbException;

public class testTmdb {

	/**
	 * @param args
	 * @throws MovieDbException 
	 */
	public static void main(String[] args) throws MovieDbException {
		Tmdb lol =  new Tmdb(new Config("20d2c5786283461e15f82c94a98cca17", "en", true, true, "",new String [0]));
		Film f = lol.getFilm(694);
		System.out.println();
	}

}
