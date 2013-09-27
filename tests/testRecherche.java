package tests;

import java.io.File;

import org.drfoliberg.films3000.data.config.Config;
import org.drfoliberg.films3000.data.scrappers.web.Tmdb;
import org.drfoliberg.films3000.models.file.MovieFile;
import org.drfoliberg.films3000.models.movie.Movie;

import com.omertron.themoviedbapi.MovieDbException;

public class testRecherche {

	/**
	 * @param args
	 * @throws MovieDbException 
	 */
	public static void main(String[] args) throws MovieDbException {
		File fichier = new File("/tank/ecole/Films/American.History.X[1998][1080p]/American.History.X[1998][1080p].mkv");
		MovieFile fic = new MovieFile(fichier);
		Tmdb t =  new Tmdb(new Config("20d2c5786283461e15f82c94a98cca17", "en", true, true, "",new String [0]));
		Movie [] films = t.rechercheFilm(fic);
		
		if(films.length > 0){
			Movie movie = t.getMovie(films[0].getId());
			System.out.println(movie.getResume());
		}
		System.out.println();
	}

}
