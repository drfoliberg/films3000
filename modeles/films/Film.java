package modeles.films;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import modeles.films.personne.InfoFilm;
import modeles.films.personne.Personne;
import modeles.images.Affiche;
import modeles.images.Fond;

import com.omertron.themoviedbapi.model.Genre;
import com.omertron.themoviedbapi.model.MovieDb;
import com.omertron.themoviedbapi.model.ProductionCountry;

/**
 * 
 * @author justin
 */
public class Film {

	private int idTmdb;
	private int annee;
	private String titre;
	private String titreOriginal;
	private String resume;
	private String idImdb;
	private int duree;
	private long dateAjout;
	private ArrayList<GenreFilm> genres;
	private ArrayList<Pays> pays;
	private ArrayList<Personne> personnes;
	private ArrayList<Duree> durees;
	private ArrayList<Affiche> affiches;
	private ArrayList<Fond> fonds;

	public Film() {
		this.personnes = new ArrayList<Personne>();
	}

	@Deprecated
	public Film(MovieDb recherche) {
		this.personnes = new ArrayList<Personne>();
		this.titre = recherche.getTitle();
		this.titreOriginal = recherche.getOriginalTitle();
		this.annee = getAnnee(recherche.getReleaseDate());
		this.idTmdb = recherche.getId();
	}

	@Deprecated
	public Film(ResultSet resultat) throws SQLException {
		this.idTmdb = resultat.getInt("tmdb_id");
		this.annee = resultat.getInt("annee");
		this.titre = resultat.getString("titre");
		this.titreOriginal = resultat.getString("titre_original");
		this.resume = resultat.getString("resume");
	}

	public Film(String titre, int annee) {
		this.annee = annee;
		this.titre = titre;
	}

	public ArrayList<Affiche> getAffiches() {
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

	public int getDuree() {
		return duree;
	}

	public ArrayList<Duree> getDurees() {
		return durees;
	}

	public ArrayList<Fond> getFonds() {
		return fonds;
	}

	public ArrayList<GenreFilm> getGenres() {
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

	public ArrayList<Pays> getPays() {
		return pays;
	}

	public ArrayList<Personne> getPersonnes() {
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

	public void setAffiches(ArrayList<Affiche> affiches) {
		this.affiches = affiches;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public void setDateAjout(long dateAjout) {
		this.dateAjout = dateAjout;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public void setDurees(ArrayList<Duree> durees) {
		this.durees = durees;
	}

	public void setFonds(ArrayList<Fond> fonds) {
		this.fonds = fonds;
	}

	public void setGenres(ArrayList<GenreFilm> genres) {
		this.genres = genres;
	}

	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
	}

	public void setIdTmdb(int idTmdb) {
		this.idTmdb = idTmdb;
	}

	@Deprecated
	public void setInfos(MovieDb infoApi) {

		genres = new ArrayList<GenreFilm>();
		pays = new ArrayList<Pays>();
		resume = infoApi.getOverview();
		duree = infoApi.getRuntime();

		for (Genre g : infoApi.getGenres()) {
			genres.add(new GenreFilm(g));
		}

		for (ProductionCountry p : infoApi.getProductionCountries()) {
			pays.add(new Pays(p));
		}

		dateAjout = Calendar.getInstance().getTimeInMillis();
		idImdb = infoApi.getImdbID();

	}

	public void setPays(ArrayList<Pays> pays) {
		this.pays = pays;
	}

	public void setPersonnes(ArrayList<InfoFilm> personnes) {
		for (InfoFilm infoFilm : personnes) {
			Personne personne = new Personne(infoFilm.getIdPersonne());
			personne.getFilmograhie().ajouterFilm(infoFilm);
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
