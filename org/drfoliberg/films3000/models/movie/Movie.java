package org.drfoliberg.films3000.models.movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.drfoliberg.films3000.models.image.Poster;
import org.drfoliberg.films3000.models.image.Backdrop;
import org.drfoliberg.films3000.models.person.PersonMovieInfo;
import org.drfoliberg.films3000.models.person.BasePerson;


import com.omertron.themoviedbapi.model.MovieDb;

/**
 * 
 * @author justin
 */
public class Movie {

	private int idTmdb;
	private int annee;
	private String titre;
	private String titreOriginal;
	private String resume;
	private String idImdb;
	private long dateAjout;
	private ArrayList<MovieGenre> genres;
	private ArrayList<Country> pays;
	private ArrayList<BasePerson> personnes;
	private ArrayList<Duration> durees;
	private ArrayList<Poster> affiches;
	private ArrayList<Backdrop> fonds;

	public Movie() {
		this.personnes = new ArrayList<BasePerson>();
	}

	public Movie(MovieDb recherche) {
		this.personnes = new ArrayList<BasePerson>();
		this.titre = recherche.getTitle();
		this.titreOriginal = recherche.getOriginalTitle();
		this.annee = getAnnee(recherche.getReleaseDate());
		this.idTmdb = recherche.getId();
	}

	@Deprecated
	public Movie(ResultSet resultat) throws SQLException {
		this.idTmdb = resultat.getInt("tmdb_id");
		this.annee = resultat.getInt("annee");
		this.titre = resultat.getString("titre");
		this.titreOriginal = resultat.getString("titre_original");
		this.resume = resultat.getString("resume");
	}

	public Movie(String titre, int annee) {
		this.annee = annee;
		this.titre = titre;
	}

	public ArrayList<Poster> getAffiches() {
		return affiches;
	}

	public int getAnnee() {
		return annee;
	}

	private int getAnnee(String dateStr) {
		int annee = 0;
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

	public long getDateAjout() {
		return dateAjout;
	}

	public ArrayList<Duration> getDurees() {
		return durees;
	}

	public ArrayList<Backdrop> getFonds() {
		return fonds;
	}

	public ArrayList<MovieGenre> getGenres() {
		return genres;
	}

	public int getId() {
		return idTmdb;
	}

	public String getIdImdb() {
		return idImdb;
	}

	public int getIdTmdb() {
		return idTmdb;
	}

	public ArrayList<Country> getPays() {
		return pays;
	}

	public ArrayList<BasePerson> getPersonnes() {
		return this.personnes;
	}

	public String getResume() {
		return resume;
	}

	public String getTitre() {
		return titre;
	}

	public String getTitreOrignal() {
		return titreOriginal;
	}

	public void setAffiches(ArrayList<Poster> affiches) {
		this.affiches = affiches;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public void setDateAjout(long dateAjout) {
		this.dateAjout = dateAjout;
	}

	public void setDurees(ArrayList<Duration> durees) {
		this.durees = durees;
	}

	public void setFonds(ArrayList<Backdrop> fonds) {
		this.fonds = fonds;
	}

	public void setGenres(ArrayList<MovieGenre> genres) {
		this.genres = genres;
	}

	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
	}

	public void setIdTmdb(int idTmdb) {
		this.idTmdb = idTmdb;
	}

	public void setPays(ArrayList<Country> pays) {
		this.pays = pays;
	}

	public void setPersonnes(ArrayList<PersonMovieInfo> personnes) {
		for (PersonMovieInfo infoFilm : personnes) {
			BasePerson personne = new BasePerson(infoFilm.getIdPersonne());
			personne.getFilmograhie().addJob(infoFilm);
			this.personnes.add(personne);
		}
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setTitreOriginal(String titreOriginal) {
		this.titreOriginal = titreOriginal;
	}

	@Override
	public String toString() {

		return titre + "(" + annee + ")";
	}
}
