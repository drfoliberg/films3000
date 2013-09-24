package org.drfoliberg.films3000.gestionDonnees.scrappers.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.drfoliberg.films3000.Constantes;
import org.drfoliberg.films3000.gestionDonnees.config.Config;
import org.drfoliberg.films3000.gestionDonnees.config.Departements;
import org.drfoliberg.films3000.modeles.fichiers.FichierFilm;
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
		return rechercheFilm(fic.getTitre(), fic.getAnnee());
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
				// TODO faire un object FilmRecherche ou changer le modèle de
				// Film actuel pour faciliter la recherche
				filmsRecherche[i] = new Film(listeRecherche.get(i));
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
	public Personne getPersonneBase(int id) throws MovieDbException {

		changerPersonne(id);
		Personne personne = new Personne(id);

		if (personneCourante.getName() == null || personneCourante.getName() == "") {
			personne.getInfoBase().setNom(Constantes.NON_DISPONIBLE);
		} else {
			personne.getInfoBase().setNom(personneCourante.getName());
		}

		if (personneCourante.getBirthday() == null || personneCourante.getBirthday() == "") {
			personne.getInfoBase().setNaissance(Constantes.NON_DISPONIBLE);
		} else {
			personne.getInfoBase().setNaissance(personneCourante.getBirthday());
		}

		if (personneCourante.getDeathday() == null || personneCourante.getDeathday() == "") {
			personne.getInfoBase().setMort(Constantes.NON_DISPONIBLE);
		} else {
			personne.getInfoBase().setMort(personneCourante.getDeathday());
		}

		if (personneCourante.getBiography() == null || personneCourante.getBiography() == "") {
			personne.getInfoBase().setBio(Constantes.NON_DISPONIBLE);
		} else {
			personne.getInfoBase().setBio(personneCourante.getBiography());
		}

		if (personneCourante.getProfilePath() == null || personneCourante.getProfilePath() == "") {
			personne.getInfoBase().setImage(Constantes.NON_DISPONIBLE);
		} else {
			personne.getInfoBase().setImage(personneCourante.getProfilePath());
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
		changerFilm(id);

		int annee = 0;
		int idTmdb = 0;
		String idImdb = "";
		String titre = "";
		String titreOriginal = "";
		String resume = "";

		ArrayList<Pays> pays = new ArrayList<>();
		ArrayList<InfoFilm> personnes = new ArrayList<>();
		ArrayList<GenreFilm> genres = new ArrayList<>();
		ArrayList<Duree> durees = new ArrayList<>();
		ArrayList<Image> images = new ArrayList<>();

		Film film = new Film();

		titre = filmCourant.getTitle();
		titreOriginal = filmCourant.getOriginalTitle();
		idTmdb = filmCourant.getId();
		idImdb = filmCourant.getImdbID();
		resume = filmCourant.getOverview();

		images = getImagesFilm(id);

		pays = this.getPays(idTmdb);
		personnes = this.getPersonnesFilm(idTmdb);
		genres = this.getGenresFilm(idTmdb);
		durees = Imdb.scrapeDurees(idImdb, idTmdb);
		annee = this.getAnneeFilm(idTmdb);
		genres = this.getGenresFilm(idTmdb);

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
		film.setAffiches(getAffichesFilm(images));
		film.setFonds(getFondsFilm(images));
		return film;
	}

	@Override
	public ArrayList<Image> getImagesFilm(int id) throws MovieDbException {
		ArrayList<Image> imagesFilm = new ArrayList<>();

		if (conf.getTouteImages()) {
			List<Artwork> images = api.getMovieImages(id, conf.getLangue());
			for (Artwork artwork : images) {
				if (artwork.getArtworkType().equals(ArtworkType.BACKDROP)) {
					Fond img = new Fond(artwork.getFilePath(), id);
					imagesFilm.add(img);
				} else if (artwork.getArtworkType().equals(ArtworkType.POSTER)) {
					Affiche img = new Affiche(artwork.getFilePath(), id, conf.getLangue());
					imagesFilm.add(img);
				}
			}
		} else {
			changerFilm(id);
			Fond fond = new Fond(filmCourant.getBackdropPath(), id);
			imagesFilm.add(fond);

			Affiche affiche = new Affiche(filmCourant.getPosterPath(), id, conf.getLangue());
			imagesFilm.add(affiche);
		}

		return imagesFilm;
	}

	@Override
	public ArrayList<Affiche> getAffichesFilm(ArrayList<Image> images) throws MovieDbException {
		ArrayList<Affiche> affiches = new ArrayList<>();
		for (Image img : images) {
			if (img instanceof Affiche) {
				affiches.add((Affiche) img);
			}
		}
		return affiches;
	}

	@Override
	public ArrayList<Fond> getFondsFilm(ArrayList<Image> images) throws MovieDbException {
		ArrayList<Fond> fonds = new ArrayList<>();
		for (Image img : images) {
			if (img instanceof Fond) {
				fonds.add((Fond) img);
			}
		}
		return fonds;
	}

	@Override
	public Personne getPersonne(int idPersonne) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InfoBase getInfoPersonne(int idPersonne) {
		InfoBase infoBase = new InfoBase();
		if (idPersonne > 0) {
			try {
				Person info = api.getPersonInfo(idPersonne);
				String nom = info.getName();
				String naissance = info.getBirthday();
				String mort = info.getDeathday();
				String bio = info.getBiography();
				String image = info.getProfilePath();

				infoBase.setIdPersonne(idPersonne);
				infoBase.setNom(nom);
				infoBase.setBio(bio);
				infoBase.setMort(mort);
				infoBase.setNaissance(naissance);
				infoBase.setImage(image);
			} catch (MovieDbException e) {
				// TODO
			}
		}

		return infoBase;
	}

	@Override
	public ArrayList<InfoFilm> getPersonnesFilm(int idFilm) {
		ArrayList<InfoFilm> personnes = new ArrayList<>();
		if (idFilm > 0) {
			try {
				List<Person> infoPersonnes = api.getMovieCasts(idFilm);
				for (Person person : infoPersonnes) {
					InfoFilm info = getInfoFilm(person);
					if (conserver(info.getJob(), info.getDepartement())) {
						info.setIdFilm(idFilm);
						personnes.add(info);
					}
				}
			} catch (MovieDbException e) {
				// TODO
			}
		}
		return personnes;
	}

	private InfoFilm getInfoFilm(Person person) {
		String special = "";
		int departement = 0;
		String job = "";
		int idPersonne = 0;
		InfoFilm infoFilm = new InfoFilm();

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
	public ArrayList<String> getCompagnies(int idFilm) {
		ArrayList<String> listeCompagnies = new ArrayList<>();
		try {
			changerFilm(idFilm);
			List<ProductionCompany> compagnies = this.filmCourant.getProductionCompanies();
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
	public ArrayList<Pays> getPays(int id) throws MovieDbException {
		changerFilm(id);
		ArrayList<Pays> pays = new ArrayList<>();
		List<ProductionCountry> paysList = filmCourant.getProductionCountries();

		for (ProductionCountry productionCountry : paysList) {
			String nom = productionCountry.getName();
			String iso = productionCountry.getIsoCode();
			Pays p = new Pays(iso, nom);
			pays.add(p);
		}

		return pays;
	}
}
