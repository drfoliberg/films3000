package org.drfoliberg.films3000.managers;

import java.util.ArrayList;

import org.drfoliberg.films3000.models.image.Poster;
import org.drfoliberg.films3000.models.image.Backdrop;
import org.drfoliberg.films3000.models.image.Image;
import org.drfoliberg.films3000.models.movie.Duration;
import org.drfoliberg.films3000.models.movie.Movie;
import org.drfoliberg.films3000.models.movie.MovieGenre;
import org.drfoliberg.films3000.models.movie.Country;
import org.drfoliberg.films3000.models.person.BasePerson;



public interface GestionFilm {
	public boolean open();

	public boolean close();

	public Movie getFilmBase(int id);

	public Movie getFilmComplet(int id);

	public ArrayList<Movie> getToutFilms();
	
	public boolean idPersonneExiste(int idPersonne);
	
	public boolean idFilmExiste(int idFilm);
	
	public boolean idgenreExiste(int idGenre);
	
	public boolean isoPaysExiste(String iso);
	
	public void supprimerDuree(int idTmdbFilm, int idVersion);
	
	public void supprimerFilm(Movie film);
	
	public void supprimerImage(Image image);
	
	public void supprimerPersonne(BasePerson personne);
	
	public void supprimerDuree(Duration duree);
	
	public void supprimerGenre(MovieGenre genre);

	public void insererFilm(Movie film);

	public void insererPersonne(BasePerson personne);

	public void insererGenre(MovieGenre genre);

	public void insererDuree(Duration duree);
	
	public void insererImage(Image image);

	public void insererPays(Country pays);

	public ArrayList<Duration> getDurees(int id);

	public ArrayList<Poster> getAffichesFilm(int id);

	public ArrayList<Backdrop> getFondsFilm(int id);

	public ArrayList<BasePerson> getPersonnesFilm(int id);

	public BasePerson getPersonneBase(int id);

}
