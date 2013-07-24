package films3000;

import gestionDonnees.scrappers.local.mediainfo.MediaInfoAnalyse;
import gestionDonnees.scrappers.web.Tmdb;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.model.MovieDb;
import composants.fichiers.FichierFilm;
import composants.fichiers.FichierFilmIncomplet;
import composants.films.Film;

public class Jozin {

	String clefApi = "";
	MediaInfoAnalyse info;
	Tmdb api;
	String langue;
	final String [] languesSupportees = {"fr","en"};
	Connection con;
	public static String racine;
	public static String racineImg;
	public static boolean cacheActive;
	
	public Jozin(Config config) throws IOException {
		con  = OutilsBD.getCon("h2", "films3000");
		info = new MediaInfoAnalyse();
	}

	public void close(){
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			films.add(new Film(resultat));
		}
		return films;
		
	}
	
	public void ajouterFichier(File fichier){
		FichierFilm fichierFilm =  new FichierFilm(fichier);
		if(!fichierFilm.analyserTitreDate()){
			JOptionPane.showMessageDialog(null, "Aucune date valide a été trouvée pour le fichier " + fichier.getName());
			//TODO demander la date et le bon titre
		}
		
		
	}

}
