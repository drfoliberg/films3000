package gestionDonnees.scrappers.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.model.Genre;
import com.omertron.themoviedbapi.model.MovieDb;
import com.omertron.themoviedbapi.model.Person;
import composants.fichiers.FichierFilm;
import composants.films.Duree;
import composants.films.Film;
import composants.films.GenreFilm;
import composants.films.Pays;
import composants.films.Personne;
import composants.images.Affiche;
import composants.images.Fond;

import films3000.Config;
import films3000.Constantes;

public class Tmdb implements WebScrapper {

	TheMovieDbApi api;
	Config conf;
	MovieDb filmCourant;
	Person personneCourante;
	String langue;

	public Tmdb(Config conf) throws MovieDbException {
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
	 * 
	 * @param titre
	 *            Titre du film à rechercher
	 * @param annee
	 *            Année du film
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
	 * @param idTmdb
	 *            L'id du film
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
	 * Méthode qui permet d'obtenir les informations de base d'une personne à
	 * l'aide de l'api de tmdb
	 * 
	 * @param id
	 *            Id de la personne
	 * @return Un object Personne avec les informations de bases seulement
	 * @throws MovieDbException
	 */
	public Personne getPersonneBase(int id) throws MovieDbException {

		changerPersonne(id);
		Personne personne = new Personne(id);

		if (personneCourante.getName() == null || personneCourante.getName() == "") {
			personne.setNom(Constantes.NON_DISPONIBLE);
		} else {
			personne.setNom(personneCourante.getName());
		}

		if (personneCourante.getBirthday() == null || personneCourante.getBirthday() == "") {
			personne.setNaissance(Constantes.NON_DISPONIBLE);
		} else {
			personne.setNaissance(personneCourante.getBirthday());
		}

		if (personneCourante.getDeathday() == null || personneCourante.getDeathday() == "") {
			personne.setMort(Constantes.NON_DISPONIBLE);
		} else {
			personne.setMort(personneCourante.getDeathday());
		}

		if (personneCourante.getBiography() == null || personneCourante.getBiography() == "") {
			personne.setBio(Constantes.NON_DISPONIBLE);
		} else {
			personne.setBio(personneCourante.getBiography());
		}

		if (personneCourante.getProfilePath() == null || personneCourante.getProfilePath() == "") {
			personne.setImage(Constantes.NON_DISPONIBLE);
		} else {
			personne.setImage(personneCourante.getProfilePath());
		}

		return personne;
	}

	/**
	 * Méthode qui fait la recherche des genres d'un film.
	 * 
	 * @param idTmdb
	 *            L'id du film
	 * @return La liste des genres d'un film
	 * @throws MovieDbException
	 */
	public ArrayList<GenreFilm> getGenresFilm(int idTmdb) throws MovieDbException {
		changerFilm(idTmdb);
		List<Genre> infoGenres = filmCourant.getGenres();
		ArrayList<GenreFilm> genres = new ArrayList<>();
		for (Genre genre : infoGenres) {
			genres.add(new GenreFilm(genre));
		}
		return genres;
	}

	/**
	 * Méthode qui retourne l'année de sortie du film en un int
	 * 
	 * @param idTmdb
	 * @return
	 * @throws MovieDbException
	 */
	public int getAnneeFilm(int idTmdb) throws MovieDbException {
		changerFilm(idTmdb);

		int annee = 0;

		String dateStr = filmCourant.getReleaseDate();

		if (dateStr != null && !dateStr.equals("null")) {
			try {
				Calendar calendrier = new GregorianCalendar();
				java.util.Date date = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(dateStr);
				calendrier.setTime(date);
				annee = calendrier.get(Calendar.YEAR);
			} catch (ParseException e) {
				annee = 1896;
				e.printStackTrace();
			}
		}
		return annee;
	}

	/**
	 * Méthode qui retourne les différentes versions d'un film en cherchant sur
	 * imdb
	 * 
	 * @param idTmdb
	 * @return La liste des durées
	 * @throws MovieDbException
	 */
	public ArrayList<Duree> getDurees(int idTmdb) throws MovieDbException {
		// TODO ajouter vérification regez de l'id imdb (tt12345678) pour éviter
		// des accès inutiles à imdb.
		changerFilm(idTmdb);
		ArrayList<Duree> durees = new ArrayList<>();
		String idImdb = filmCourant.getImdbID();
		durees.addAll(Imdb.scrapeDurees(idImdb, idTmdb));
		if (durees.size() == 0) {
			durees.add(new Duree(filmCourant.getRuntime(), "N/A", idTmdb, 0));
		}
		return durees;
	}

	/**
	 * Méthode qui vérifie si le dernier film cherché est toujours le bon et
	 * fait une nouvelle requête si nécessaire.
	 * 
	 * @param idTmdb
	 * @throws MovieDbException
	 */
	private void changerFilm(int idTmdb) throws MovieDbException {
		if (filmCourant == null || filmCourant.getId() != idTmdb || this.langue != conf.getLangue()) {
			this.filmCourant = api.getMovieInfo(idTmdb, conf.getLangue());
			this.langue = conf.getLangue();
		}
	}

	/**
	 * Méthode qui vérifie si la dernière personne cherchée est toujours la
	 * bonne et fait une nouvelle requête si nécessaire.
	 * 
	 * @param id
	 * @throws MovieDbException
	 */
	private void changerPersonne(int id) throws MovieDbException {
		if (personneCourante == null || personneCourante.getId() != id) {
			// TODO ajouter le support de la langue pour les informations des
			// personnes. (tmdb ne le fait pas encore)
			this.personneCourante = api.getPersonInfo(id);
		}
	}

	@Override
	public Film getFilm(int id) throws MovieDbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Affiche> getAffichesFilm(int id) throws MovieDbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Fond> getFondsFilm(int id) throws MovieDbException {
		// TODO Auto-generated method stub
		return null;
	}

}
