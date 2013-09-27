package org.drfoliberg.films3000.data.scrappers.web;

import java.util.ArrayList;

import org.drfoliberg.films3000.models.image.Backdrop;
import org.drfoliberg.films3000.models.image.Image;
import org.drfoliberg.films3000.models.image.Poster;
import org.drfoliberg.films3000.models.movie.Country;
import org.drfoliberg.films3000.models.movie.Duration;
import org.drfoliberg.films3000.models.movie.Movie;
import org.drfoliberg.films3000.models.movie.MovieGenre;
import org.drfoliberg.films3000.models.person.PersonInfo;
import org.drfoliberg.films3000.models.person.PersonMovieInfo;

import com.omertron.themoviedbapi.MovieDbException;

/**
 * Interface qui contient les méthodes à implémenter pour un service d'accès aux
 * données par le web
 * 
 * @author justin
 * 
 */
public interface WebScrapper {

	public Movie getMovie(int id) throws MovieDbException;

	public ArrayList<Duration> getVersions(int id) throws MovieDbException;

	public ArrayList<Image> getMovieArt(int id) throws MovieDbException;
	
	public ArrayList<Backdrop> getBackdrops(ArrayList<Image> images) throws MovieDbException;
	
	public ArrayList<Poster> getPosters(ArrayList<Image> images) throws MovieDbException;
	
	public ArrayList<Country> getCountry(int id) throws MovieDbException;

	public Movie[] searchFor(String title, int year) throws MovieDbException;

	public Movie[] searchFor(String title) throws MovieDbException;

	public ArrayList<MovieGenre> getMovieGenres(int id) throws MovieDbException;
	
	public PersonInfo getPersonInfo(int idPersonne);
	
	public ArrayList<PersonMovieInfo> getMovieCast(int idFilm);
	
	public ArrayList<String> getCompanies(int idFilm);
}
