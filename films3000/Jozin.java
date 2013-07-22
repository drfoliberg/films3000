package films3000;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import mediainfo.InfosLocales;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.model.MovieDb;
import composants.fichiers.FichierFilm;
import composants.fichiers.FichierFilmIncomplet;
import composants.films.Film;

public class Jozin {

	String clefApi = "";
	InfosLocales info;
	TheMovieDbApi api;
	String langue;
	final String [] languesSupportees = {"fr","en"};
	Connection con;
	public static String racine;
	public static String racineImg;
	public static boolean cacheActive;
	
	public Jozin(String clefApi, String racine, Date dateDerniereVer,
			String langue, String racineImages) throws IOException {
		try {

			if (!Arrays.asList(languesSupportees).contains(langue)) {
				throw new Error("Langue " + langue + " non supportée");
			} else {
				this.langue = langue;
			}
			con  = OutilsBD.getCon("h2", "films3000");
			info = new InfosLocales();
			api = new TheMovieDbApi(clefApi);
			cacheActive = true;
			Jozin.racine = racine;
			if(racineImages==null || racineImages.equals("")){
				racineImages = "img";
			}
			Jozin.racineImg = racineImages;
		} catch (MovieDbException e) {
			e.printStackTrace();
		}
	}
	
	public void setLangue(String langue){
		this.langue = langue;
	}
	
	public void setCache(boolean active){
		Jozin.cacheActive = active;
	}
	
	public void setRacineImg(String r){
		Jozin.racineImg = r;
	}
	
	public void setRacine(String r){
		Jozin.racine = r;
	}
	
	public void close(){
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Film[] rechercheFilm(String titre) {

		return rechercheFilm(titre, 0);

	}

	public Film[] rechercheFilm(FichierFilm fic) {
		return rechercheFilm(fic.titre, fic.annee);
	}

	public Film[] rechercheFilm(String titre, int annee) {
		List<MovieDb> listeRecherche = null;
		Film[] filmsRecherche = null;
		try {
			listeRecherche = api.searchMovie(titre, annee, langue, false, 0);
			filmsRecherche = new Film[listeRecherche.size()];

			for (int i = 0; i < filmsRecherche.length; i++) {
				filmsRecherche[i] = new Film(listeRecherche.get(i));
			}

		} catch (MovieDbException e) {
			e.printStackTrace();
		}
		return filmsRecherche;
	}
	
	public URL getUrl(String chemin){
		try {
			return api.createImageUrl(chemin, "w1280");
		} catch (MovieDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	private Film getInfosFilm(int idTmdb) throws MovieDbException {
		Film film = null;
		MovieDb filmAPI = api.getMovieInfo(idTmdb, langue);
		film = new Film(filmAPI);
		film.setInfos(filmAPI);
		film.setPersonnes(api.getMovieCasts(idTmdb));
		return film;
	}
	

	public void analyseStream(FichierFilm fic){
		info.getInfosFichier(fic);
	}
	
	public FichierFilm nouveauFichier(File fic){
		FichierFilm fichierFilm = new FichierFilm(fic);
		fichierFilm.analyserTitreDate();
		return fichierFilm;
	}
	
	public void lier(FichierFilm fic, int idTmdb){
		fic.id_tmdb = idTmdb;
	}
	
	/**
	 * 
	 * @param ficFilm Le fichierFilm a insérer, doit avoir son bon id_tmdb
	 * @return le succès de l'enregistrement
	 * @throws FichierFilmIncomplet
	 * @throws MovieDbException
	 * @throws SQLException 
	 */
	public boolean enregistrerFichier(FichierFilm ficFilm) throws FichierFilmIncomplet, MovieDbException, SQLException{
		
		if(!ficFilm.existe(con)){
			//regarder si le fichier contient un id de film
			if(ficFilm.id_tmdb == 0){
				throw new FichierFilmIncomplet(ficFilm);
			}
			
			analyseStream(ficFilm);
		
			ficFilm.inserer(con);
			ficFilm.insererStreams(con);

			if(!Film.existe(con, ficFilm.id_tmdb)){
				Film film = getInfosFilm(ficFilm.id_tmdb);
				film.inserer(api, con,langue);
			}
		}
		
		return false;
	}
	
	public ArrayList<Film> getTousFilms() throws SQLException{
		ArrayList<Film> films = new ArrayList<>();
		java.sql.PreparedStatement commande = con.prepareStatement("Select * from films order by titre");
		ResultSet resultat = commande.executeQuery();
		while(resultat.next()){
			films.add(new Film(resultat.getString("titre"),resultat.getInt("annee")));
		}
		return films;
		
	}
}
