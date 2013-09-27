package org.drfoliberg.films3000.data.scrappers.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.drfoliberg.films3000.data.config.Config;
import org.drfoliberg.films3000.data.config.Departements;
import org.drfoliberg.films3000.data.constants.Constantes;
import org.drfoliberg.films3000.models.file.MovieFile;
import org.drfoliberg.films3000.models.image.Poster;
import org.drfoliberg.films3000.models.image.Backdrop;
import org.drfoliberg.films3000.models.image.Image;
import org.drfoliberg.films3000.models.movie.Duration;
import org.drfoliberg.films3000.models.movie.Movie;
import org.drfoliberg.films3000.models.movie.MovieGenre;
import org.drfoliberg.films3000.models.movie.Country;
import org.drfoliberg.films3000.models.person.PersonInfo;
import org.drfoliberg.films3000.models.person.PersonMovieInfo;
import org.drfoliberg.films3000.models.person.BasePerson;


import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.model.Artwork;
import com.omertron.themoviedbapi.model.ArtworkType;
import com.omertron.themoviedbapi.model.Genre;
import com.omertron.themoviedbapi.model.MovieDb;
import com.omertron.themoviedbapi.model.Person;
import com.omertron.themoviedbapi.model.ProductionCompany;
import com.omertron.themoviedbapi.model.ProductionCountry;

public class Tmdb implements WebScrapper {

	TheMovieDbApi api;
	Config conf;
	MovieDb cachedMovie; //TODO link with cache manager
	Person cachedPerson; //TODO link with cache manager
	String language;

	public Tmdb(Config conf) throws MovieDbException {
		this.conf = conf;
		api = new TheMovieDbApi(conf.getClefApi());
	}

	public Movie[] searchFor(String title) {
		return searchFor(title, 0);
	}

	public Movie[] rechercheFilm(MovieFile fic) {
		return searchFor(fic.getTitre(), fic.getAnnee());
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
	public Movie[] searchFor(String titre, int annee) {
		List<MovieDb> listeRecherche = null;
		Movie[] filmsRecherche = null;
		try {
			listeRecherche = api.searchMovie(titre, annee, conf.getLangue(), false, 0);
			filmsRecherche = new Movie[listeRecherche.size()];

			for (int i = 0; i < filmsRecherche.length; i++) {
				// TODO faire un object FilmRecherche ou changer le modèle de
				// Film actuel pour faciliter la recherche
				filmsRecherche[i] = new Movie(listeRecherche.get(i));
			}

		} catch (MovieDbException e) {
			e.printStackTrace();
		}
		return filmsRecherche;
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
	public BasePerson getPersonneBase(int id) throws MovieDbException {

		changerPersonne(id);
		BasePerson personne = new BasePerson(id);

		if (cachedPerson.getName() == null || cachedPerson.getName() == "") {
			personne.getInfoBase().setName(Constantes.NOT_AVAILABLE);
		} else {
			personne.getInfoBase().setName(cachedPerson.getName());
		}

		if (cachedPerson.getBirthday() == null || cachedPerson.getBirthday() == "") {
			personne.getInfoBase().setBirthday(Constantes.NOT_AVAILABLE);
		} else {
			personne.getInfoBase().setBirthday(cachedPerson.getBirthday());
		}

		if (cachedPerson.getDeathday() == null || cachedPerson.getDeathday() == "") {
			personne.getInfoBase().setDeathday(Constantes.NOT_AVAILABLE);
		} else {
			personne.getInfoBase().setDeathday(cachedPerson.getDeathday());
		}

		if (cachedPerson.getBiography() == null || cachedPerson.getBiography() == "") {
			personne.getInfoBase().setBio(Constantes.NOT_AVAILABLE);
		} else {
			personne.getInfoBase().setBio(cachedPerson.getBiography());
		}

		if (cachedPerson.getProfilePath() == null || cachedPerson.getProfilePath() == "") {
			personne.getInfoBase().setImage(Constantes.NOT_AVAILABLE);
		} else {
			personne.getInfoBase().setImage(cachedPerson.getProfilePath());
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
	public ArrayList<MovieGenre> getMovieGenres(int idTmdb) throws MovieDbException {
		changerFilm(idTmdb);
		List<Genre> infoGenres = cachedMovie.getGenres();
		ArrayList<MovieGenre> genres = new ArrayList<>();
		for (Genre genre : infoGenres) {
			genres.add(new MovieGenre(genre));
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

		String dateStr = cachedMovie.getReleaseDate();

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
	public ArrayList<Duration> getVersions(int idTmdb) throws MovieDbException {
		// TODO ajouter vérification regez de l'id imdb (tt12345678) pour éviter
		// des accès inutiles à imdb.
		changerFilm(idTmdb);
		ArrayList<Duration> durees = new ArrayList<>();
		String idImdb = cachedMovie.getImdbID();
		durees.addAll(Imdb.scrapeDurees(idImdb, idTmdb));
		if (durees.size() == 0) {
			durees.add(new Duration(cachedMovie.getRuntime(), "N/A", idTmdb, 0));
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
		if (cachedMovie == null || cachedMovie.getId() != idTmdb || this.language != conf.getLangue()) {
			this.cachedMovie = api.getMovieInfo(idTmdb, conf.getLangue());
			this.language = conf.getLangue();
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
		if (cachedPerson == null || cachedPerson.getId() != id) {
			// TODO ajouter le support de la langue pour les informations des
			// personnes. (tmdb ne le fait pas encore)
			this.cachedPerson = api.getPersonInfo(id);
		}
	}

	@Override
	public Movie getMovie(int id) throws MovieDbException {
		changerFilm(id);

		int annee = 0;
		int idTmdb = 0;
		String idImdb = "";
		String titre = "";
		String titreOriginal = "";
		String resume = "";

		ArrayList<Country> pays = new ArrayList<>();
		ArrayList<PersonMovieInfo> personnes = new ArrayList<>();
		ArrayList<MovieGenre> genres = new ArrayList<>();
		ArrayList<Duration> durees = new ArrayList<>();
		ArrayList<Image> images = new ArrayList<>();

		Movie film = new Movie();

		titre = cachedMovie.getTitle();
		titreOriginal = cachedMovie.getOriginalTitle();
		idTmdb = cachedMovie.getId();
		idImdb = cachedMovie.getImdbID();
		resume = cachedMovie.getOverview();

		images = getMovieArt(id);

		pays = this.getCountry(idTmdb);
		personnes = this.getMovieCast(idTmdb);
		genres = this.getMovieGenres(idTmdb);
		durees = Imdb.scrapeDurees(idImdb, idTmdb);
		annee = this.getAnneeFilm(idTmdb);
		genres = this.getMovieGenres(idTmdb);

		film.setTitre(titre);
		film.setTitreOriginal(titreOriginal);
		film.setAnnee(annee);
		film.setDateAjout(System.currentTimeMillis());
		film.setIdImdb(idImdb);
		film.setIdTmdb(idTmdb);
		film.setPays(pays);
		film.setGenres(genres);
		film.setResume(resume);
		film.setPersonnes(personnes);
		film.setDurees(durees);
		film.setAffiches(getPosters(images));
		film.setFonds(getBackdrops(images));
		return film;
	}

	@Override
	public ArrayList<Image> getMovieArt(int id) throws MovieDbException {
		ArrayList<Image> imagesFilm = new ArrayList<>();

		if (conf.getTouteImages()) {
			List<Artwork> images = api.getMovieImages(id, conf.getLangue());
			for (Artwork artwork : images) {
				if (artwork.getArtworkType().equals(ArtworkType.BACKDROP)) {
					Backdrop img = new Backdrop(artwork.getFilePath(), id);
					imagesFilm.add(img);
				} else if (artwork.getArtworkType().equals(ArtworkType.POSTER)) {
					Poster img = new Poster(artwork.getFilePath(), id, conf.getLangue());
					imagesFilm.add(img);
				}
			}
		} else {
			changerFilm(id);
			Backdrop fond = new Backdrop(cachedMovie.getBackdropPath(), id);
			imagesFilm.add(fond);

			Poster affiche = new Poster(cachedMovie.getPosterPath(), id, conf.getLangue());
			imagesFilm.add(affiche);
		}

		return imagesFilm;
	}

	@Override
	public ArrayList<Poster> getPosters(ArrayList<Image> images) throws MovieDbException {
		ArrayList<Poster> affiches = new ArrayList<>();
		for (Image img : images) {
			if (img instanceof Poster) {
				affiches.add((Poster) img);
			}
		}
		return affiches;
	}

	@Override
	public ArrayList<Backdrop> getBackdrops(ArrayList<Image> images) throws MovieDbException {
		ArrayList<Backdrop> fonds = new ArrayList<>();
		for (Image img : images) {
			if (img instanceof Backdrop) {
				fonds.add((Backdrop) img);
			}
		}
		return fonds;
	}

	@Override
	public PersonInfo getPersonInfo(int idPersonne) {
		PersonInfo infoBase = new PersonInfo();
		if (idPersonne > 0) {
			try {
				Person info = api.getPersonInfo(idPersonne);
				String nom = info.getName();
				String naissance = info.getBirthday();
				String mort = info.getDeathday();
				String bio = info.getBiography();
				String image = info.getProfilePath();

				infoBase.setPersonId(idPersonne);
				infoBase.setName(nom);
				infoBase.setBio(bio);
				infoBase.setDeathday(mort);
				infoBase.setBirthday(naissance);
				infoBase.setImage(image);
			} catch (MovieDbException e) {
				// TODO
			}
		}

		return infoBase;
	}

	@Override
	public ArrayList<PersonMovieInfo> getMovieCast(int idFilm) {
		ArrayList<PersonMovieInfo> cast = new ArrayList<>();
		if (idFilm > 0) {
			try {
				List<Person> infoPersonnes = api.getMovieCasts(idFilm);
				for (Person person : infoPersonnes) {
					PersonMovieInfo info = getInfoFilm(person);
					if (conserver(info.getJob(), info.getDepartement())) {
						info.setIdFilm(idFilm);
						cast.add(info);
					}
				}
			} catch (MovieDbException e) {
				// TODO
			}
		}
		return cast;
	}

	private PersonMovieInfo getInfoFilm(Person person) {
		String special = "";
		int departement = 0;
		String job = "";
		int idPersonne = 0;
		PersonMovieInfo infoFilm = new PersonMovieInfo();

		idPersonne = person.getId();
		job = person.getJob();
		departement = getDepartement(person.getDepartment());
		special = getSpecial(person);

		infoFilm.setIdPersonne(idPersonne);
		infoFilm.setDepartement(departement);
		infoFilm.setJob(job);
		infoFilm.setSpecial(special);

		return infoFilm;
	}

	private int getDepartement(String departement) {
		int no = 0;
		no = Arrays.asList(Departements.DEPARTEMENTS).indexOf(departement);
		return no;

	}

	private String getSpecial(Person person) {
		String special = "";
		if (person.getJob().equals("actor")) {
			special = person.getCharacter();
		}

		return special;
	}

	private boolean conserver(String job, int departement) {
		boolean conserver = false;

		switch (departement) {
		case (2):
			conserver = true;
			break;
		case (0):
			if (Departements.WRITING.getJobsConserver().contains(job)) {
				conserver = true;
			}
			break;
		case (1):
			if (Departements.DIRECTING.getJobsConserver().contains(job)) {
				conserver = true;
			}
			break;
		case (3):
			if (Departements.CAMERA.getJobsConserver().contains(job)) {
				conserver = true;
			}
			break;
		case (4):
			if (Departements.EDITING.getJobsConserver().contains(job)) {
				conserver = true;
			}
			break;
		case (7):
			if (Departements.PRODUCTION.getJobsConserver().contains(job)) {
				conserver = true;
			}
			break;
		case (9):
			if (Departements.SOUND.getJobsConserver().contains(job)) {
				conserver = true;
			}
			break;
		}

		return conserver;

	}

	@Override
	public ArrayList<String> getCompanies(int idFilm) {
		ArrayList<String> listeCompagnies = new ArrayList<>();
		try {
			changerFilm(idFilm);
			List<ProductionCompany> compagnies = this.cachedMovie.getProductionCompanies();
			for (ProductionCompany productionCompany : compagnies) {
				listeCompagnies.add(productionCompany.getName());
			}
		} catch (MovieDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listeCompagnies;
	}

	@Override
	public ArrayList<Country> getCountry(int id) throws MovieDbException {
		changerFilm(id);
		ArrayList<Country> pays = new ArrayList<>();
		List<ProductionCountry> paysList = cachedMovie.getProductionCountries();

		for (ProductionCountry productionCountry : paysList) {
			String nom = productionCountry.getName();
			String iso = productionCountry.getIsoCode();
			Country p = new Country(iso, nom);
			pays.add(p);
		}

		return pays;
	}
}
