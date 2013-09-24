package tests;

import java.io.File;

import org.drfoliberg.films3000.gestionDonnees.config.Config;
import org.drfoliberg.films3000.gestionDonnees.scrappers.web.Tmdb;
import org.drfoliberg.films3000.modeles.fichiers.FichierFilm;
import org.drfoliberg.films3000.modeles.films.Film;

import com.omertron.themoviedbapi.MovieDbException;

public class testRecherche {

	/**
	 * @param args
	 * @throws MovieDbException 
	 */
	public static void main(String[] args) throws MovieDbException {
		File fichier = new File("/tank/ecole/Films/American.History.X[1998][1080p]/American.History.X[1998][1080p].mkv");
		FichierFilm fic = new FichierFilm(fichier);
		Tmdb t =  new Tmdb(new Config("20d2c5786283461e15f82c94a98cca17", "en", true, true, "",new String [0]));
		Film [] films = t.rechercheFilm(fic);
		System.out.println();
	}

}
