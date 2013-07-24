package gestionDonnees.scrappers.web;

import java.util.ArrayList;

import com.omertron.themoviedbapi.MovieDbException;
import composants.films.Duree;
import composants.films.Film;
import composants.films.GenreFilm;
import composants.films.Personne;
import composants.images.Affiche;
import composants.images.Fond;

/**
 * Interface qui contient les méthodes à implémenter pour un service d'accès aux
 * données par le web
 * 
 * @author justin
 * 
 */
public interface WebScrapper {

	public Film getFilm(int id) throws MovieDbException;

	public ArrayList<Duree> getDurees(int id) throws MovieDbException;

	public ArrayList<Affiche> getAffichesFilm(int id) throws MovieDbException;

	public ArrayList<Fond> getFondsFilm(int id) throws MovieDbException;

	public ArrayList<Personne> getPersonnesFilm(int id) throws MovieDbException;

	public Personne getPersonneBase(int id) throws MovieDbException;

	public Film[] rechercheFilm(String titre, int annee) throws MovieDbException;

	public Film[] rechercheFilm(String titre) throws MovieDbException;

	public ArrayList<GenreFilm> getGenresFilm(int id) throws MovieDbException;
}
