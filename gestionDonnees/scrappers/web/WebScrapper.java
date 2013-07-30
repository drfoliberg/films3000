package gestionDonnees.scrappers.web;

import java.util.ArrayList;

import modeles.films.Duree;
import modeles.films.Film;
import modeles.films.GenreFilm;
import modeles.films.personne.Filmographie;
import modeles.films.personne.InfoBase;
import modeles.films.personne.InfoFilm;
import modeles.films.personne.Personne;
import modeles.images.Affiche;
import modeles.images.Fond;

import com.omertron.themoviedbapi.MovieDbException;

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

	//public ArrayList<Personne> getPersonnesFilm(int id) throws MovieDbException;

	public Film[] rechercheFilm(String titre, int annee) throws MovieDbException;

	public Film[] rechercheFilm(String titre) throws MovieDbException;

	public ArrayList<GenreFilm> getGenresFilm(int id) throws MovieDbException;
	
	public Personne getPersonne(int idPersonne);
	
	public InfoBase getInfoPersonne(int idPersonne);
	
	public ArrayList<InfoFilm> getPersonnesFilm(int idFilm);
}
