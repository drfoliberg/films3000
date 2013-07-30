package modeles.films;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import modeles.films.personne.Personne;
import modeles.images.Affiche;
import modeles.images.Fond;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.model.Artwork;
import com.omertron.themoviedbapi.model.ArtworkType;
import com.omertron.themoviedbapi.model.Genre;
import com.omertron.themoviedbapi.model.MovieDb;
import com.omertron.themoviedbapi.model.Person;
import com.omertron.themoviedbapi.model.ProductionCountry;

/**
 * 
 * @author justin
 */
public class Film {

	private int idTmdb;
	private int annee;
	private String titre;
	private String titreOrignal;
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

	public Film(MovieDb recherche) {
		this.personnes = new ArrayList<Personne>();
		this.titre = recherche.getTitle();
		this.titreOrignal = recherche.getOriginalTitle();
		this.annee = getAnnee(recherche.getReleaseDate());
		this.idTmdb = recherche.getId();
	}

	public Film(ResultSet resultat) throws SQLException {
		this.idTmdb = resultat.getInt("tmdb_id");
		this.annee = resultat.getInt("annee");
		this.titre = resultat.getString("titre");
		this.titreOrignal = resultat.getString("titre_original");
		this.resume = resultat.getString("resume");
	}

	public Film(String titre, int annee) {
		this.annee = annee;
		this.titre = titre;
	}

	public ArrayList<Personne> getPersonnes() {
		return this.personnes;
	}

	public void setPersonnes(List<Person> personnes) throws MovieDbException {
		for (Person p : personnes) {
			ajouterPersonne(p);
		}
	}

	private void ajouterPersonne(Person p) throws MovieDbException {
		Personne tmp = new Personne(p);
		tmp.setInfosFilm(p);
		this.personnes.add(tmp);
	}

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

//	public boolean inserer(TheMovieDbApi api, Connection con, String langue) throws SQLException, MovieDbException {
//
//		for (Personne p : getPersonnes()) {
//			p.insererPersonneFilm(idTmdb, api, con);
//		}
//
//		for (Pays p : pays) {
//			p.enregistrer(con, idTmdb);
//		}
//
//		for (GenreFilm g : genres) {
//			g.enregistrer(con, idTmdb);
//		}
//
//		List<Artwork> images = api.getMovieImages(idTmdb, "");
//
//		for (Artwork a : images) {
//			if (a.getArtworkType().equals(ArtworkType.BACKDROP)) {
//				Fond fond = new Fond(a.getFilePath(), idTmdb);
//				fond.inserer(con);
//			} else if (a.getArtworkType().equals(ArtworkType.POSTER)) {
//				Affiche affiche = new Affiche(a.getFilePath(), idTmdb);
//				affiche.inserer(con);
//			}
//		}
//
//		String sql = "insert into films (tmdb_id,imdb_id,annee,titre,titre_original,resume,dateAjout) values ("
//				+ "?,?,?,?,?,?,?)";
//		java.sql.PreparedStatement commande = con.prepareStatement(sql);
//		commande.setInt(1, idTmdb);
//		commande.setString(2, idImdb);
//		commande.setInt(3, annee);
//		commande.setString(4, titre);
//		commande.setString(5, titreOrignal);
//		commande.setString(6, resume);
//		commande.setLong(7, dateAjout);
//		return commande.execute();
//	}

	public static boolean existe(Connection con, int id_tmdb) throws SQLException {
		boolean existe = false;
		java.sql.PreparedStatement commande = con.prepareStatement("Select tmdb_id from films where tmdb_id = "
				+ id_tmdb);
		ResultSet resultat = commande.executeQuery();
		if (resultat.first()) {
			existe = true;
		}
		resultat.close();
		commande.close();
		return existe;

	}

	public int getId() {
		return idTmdb;
	}

	public int getIdTmdb() {
		return idTmdb;
	}

	public int getAnnee() {
		return annee;
	}

	public String getTitre() {
		return titre;
	}

	public String getTitreOrignal() {
		return titreOrignal;
	}

	public String getResume() {
		return resume;
	}

	public String getIdImdb() {
		return idImdb;
	}

	public int getDuree() {
		return duree;
	}

	public long getDateAjout() {
		return dateAjout;
	}

	public ArrayList<GenreFilm> getGenres() {
		return genres;
	}

	public ArrayList<Pays> getPays() {
		return pays;
	}

	public ArrayList<Duree> getDurees() {
		return durees;
	}

	@Override
	public String toString() {

		return titre + "(" + annee + ")";
	}
}
