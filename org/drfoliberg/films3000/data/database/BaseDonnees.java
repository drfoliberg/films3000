package org.drfoliberg.films3000.data.database;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

import org.drfoliberg.films3000.data.database.structure.Colonne;
import org.drfoliberg.films3000.data.database.structure.Structure;
import org.drfoliberg.films3000.data.database.structure.Table;
import org.drfoliberg.films3000.managers.GestionFichiersFilm;
import org.drfoliberg.films3000.managers.GestionFilm;
import org.drfoliberg.films3000.models.file.MovieFile;
import org.drfoliberg.films3000.models.image.Poster;
import org.drfoliberg.films3000.models.image.Backdrop;
import org.drfoliberg.films3000.models.image.Image;
import org.drfoliberg.films3000.models.movie.Duration;
import org.drfoliberg.films3000.models.movie.Movie;
import org.drfoliberg.films3000.models.movie.MovieGenre;
import org.drfoliberg.films3000.models.movie.Country;
import org.drfoliberg.films3000.models.person.BasePerson;


public abstract class BaseDonnees implements GestionFilm, GestionFichiersFilm {
	
	protected String nom;
	protected String url;
	protected Connection connection;

	public abstract boolean open();

	public abstract boolean close();

	public abstract Colonne getColonne(String nomColonne);

	public abstract Structure getStructure();

	public abstract Table getTable(String nomTable);

	public abstract void mettreEnPlaceTables();

	public abstract boolean baseDonneesExiste();

	public abstract void creerBaseDonnees();

	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Connection getConnection() {
		return connection;
	}

	@Override
	public Movie getFilmBase(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie getFilmComplet(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Movie> getToutFilms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean idPersonneExiste(int idPersonne) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean idFilmExiste(int idFilm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean idgenreExiste(int idGenre) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isoPaysExiste(String iso) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void supprimerDuree(int idTmdbFilm, int idVersion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerFilm(Movie film) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerImage(Image image) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerPersonne(BasePerson personne) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerDuree(Duration duree) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerGenre(MovieGenre genre) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insererFilm(Movie film) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insererPersonne(BasePerson personne) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insererGenre(MovieGenre genre) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insererDuree(Duration duree) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insererImage(Image image) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insererPays(Country pays) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Duration> getDurees(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Poster> getAffichesFilm(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Backdrop> getFondsFilm(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BasePerson> getPersonnesFilm(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasePerson getPersonneBase(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MovieFile> getFichiers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerFichierFilm(int idFichier) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<MovieFile> getFichierFilm(File fichier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerFichierFilm(MovieFile fichier) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ajouterFichierFilm(MovieFile fichier) {
		// TODO Auto-generated method stub

	}

}
