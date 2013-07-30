package tests;

import java.util.ArrayList;

import modeles.films.personne.InfoFilm;

import com.omertron.themoviedbapi.MovieDbException;

import films3000.Config;
import gestionDonnees.scrappers.web.Tmdb;

public class testTmdb {

	/**
	 * @param args
	 * @throws MovieDbException 
	 */
	public static void main(String[] args) throws MovieDbException {
		Tmdb lol =  new Tmdb(new Config("20d2c5786283461e15f82c94a98cca17", "en", true, true, "",new String [0]));
		ArrayList<InfoFilm> personnes = lol.getPersonnesFilm(62);
	}

}
