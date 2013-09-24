package org.drfoliberg.films3000.gestionDonnees.scrappers.web;

import java.util.ArrayList;

import org.drfoliberg.films3000.modeles.films.Duree;
import org.drfoliberg.films3000.modeles.films.Film;
import org.drfoliberg.films3000.modeles.films.GenreFilm;
import org.drfoliberg.films3000.modeles.films.Pays;
import org.drfoliberg.films3000.modeles.films.personne.InfoBase;
import org.drfoliberg.films3000.modeles.films.personne.InfoFilm;
import org.drfoliberg.films3000.modeles.films.personne.Personne;
import org.drfoliberg.films3000.modeles.images.Affiche;
import org.drfoliberg.films3000.modeles.images.Fond;
import org.drfoliberg.films3000.modeles.images.Image;


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

	public ArrayList<Image> getImagesFilm(int id) throws MovieDbException;
	
	public ArrayList<Fond> getFondsFilm(ArrayList<Image> images) throws MovieDbException;
	
	public ArrayList<Affiche> getAffichesFilm(ArrayList<Image> images) throws MovieDbException;
	
	public ArrayList<Pays> getPays(int id) throws MovieDbException;

	//public ArrayList<Personne> getPersonnesFilm(int id) throws MovieDbException;

	public Film[] rechercheFilm(String titre, int annee) throws MovieDbException;

	public Film[] rechercheFilm(String titre) throws MovieDbException;

	public ArrayList<GenreFilm> getGenresFilm(int id) throws MovieDbException;
	
	public Personne getPersonne(int idPersonne);
	
	public InfoBase getInfoPersonne(int idPersonne);
	
	public ArrayList<InfoFilm> getPersonnesFilm(int idFilm);
	
	public ArrayList<String> getCompagnies(int idFilm);
}
