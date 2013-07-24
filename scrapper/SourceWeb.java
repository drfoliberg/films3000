package scrapper;

import java.util.ArrayList;
import java.util.List;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.model.Genre;
import com.omertron.themoviedbapi.model.MovieDb;
import com.omertron.themoviedbapi.model.Person;
import composants.fichiers.FichierFilm;
import composants.films.Duree;
import composants.films.Film;
import composants.films.Personne;

import films3000.Config;

public class SourceWeb {
	
	TheMovieDbApi api;
	Config conf;
	MovieDb filmCourant;
	String langue;
	
	
	public SourceWeb(Config conf) throws MovieDbException{
		this.conf = conf;
		api = new TheMovieDbApi(conf.getClefApi());
	}
	
	public Film[] rechercheFilm(String titre) {
		return rechercheFilm(titre, 0);
	}

	public Film[] rechercheFilm(FichierFilm fic) {
		return rechercheFilm(fic.titre, fic.annee);
	}

	/**
	 * Méthode qui fait la recherche sur tmdb
	 * @param titre Titre du film à rechercher
	 * @param annee Année du film
	 * @return
	 */
	public Film[] rechercheFilm(String titre, int annee) {
		List<MovieDb> listeRecherche = null;
		Film[] filmsRecherche = null;
		try {
			listeRecherche = api.searchMovie(titre, annee, conf.getLangue(), false, 0);
			filmsRecherche = new Film[listeRecherche.size()];

			for (int i = 0; i < filmsRecherche.length; i++) {
				filmsRecherche[i] = new Film(listeRecherche.get(i));
			}

		} catch (MovieDbException e) {
			e.printStackTrace();
		}
		return filmsRecherche;
	}
	
	/**
	 * 
	 * @param idTmdb L'id du film
	 * @return La liste des personnes pour un film
	 * @throws MovieDbException
	 */
	public ArrayList<Personne> getPersonnesFilm(int idTmdb) throws MovieDbException {
		List<Person> cast = api.getMovieCasts(idTmdb);
		ArrayList<Personne> personnes = new ArrayList<>();

		for (Person person : cast) {
			Personne personneInfo = new Personne(person);
			personneInfo.setInfosFilm(person);
			if (personneInfo.isConserver()) {
				personnes.add(personneInfo);
			}
		}

		return personnes;
	}
	
	/**
	 * Méthode qui fait la recherche des genres d'un film.
	 * @param idTmdb L'id du film
	 * @return La liste des genres d'un film
	 * @throws MovieDbException 
	 */
	public List<Genre> getGenresFilm(int idTmdb) throws MovieDbException{
		changerFilm(idTmdb);
		return filmCourant.getGenres();
	}
	
	
	/**
	 * Méthode qui retourne les différentes versions d'un film en cherchant sur imdb
	 * @param idImdb L'id IMDB et non tmdb
	 * @return La liste des durées
	 */
	public ArrayList<Duree> getDurees(String idImdb){
		//TODO ajouter vérification regez de l'id imdb (tt12345678) pour éviter des accès inutiles à imdb.
		return Imdb.scrapeDurees(idImdb);
	}
	
	/**
	 * Méthode qui vérifie si le dernier résultat cherché est toujours le bon et fait une nouvelle requête si nécessaire.
	 * @param idTmdb
	 * @throws MovieDbException
	 */
	private void changerFilm(int idTmdb) throws MovieDbException{
		if(filmCourant == null || filmCourant.getId() != idTmdb || this.langue != conf.getLangue()) {
			this.filmCourant = api.getMovieInfo(idTmdb, conf.getLangue());
			this.langue = conf.getLangue();
		}
	}

}
