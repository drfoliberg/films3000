package gestionDonnees.donnees.baseDonnees;

import gestionDonnees.donnees.GestionFichiersFilm;
import gestionDonnees.donnees.GestionFilm;
import gestionDonnees.donnees.baseDonnees.structure.Colonne;
import gestionDonnees.donnees.baseDonnees.structure.Structure;
import gestionDonnees.donnees.baseDonnees.structure.Table;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

import modeles.fichiers.FichierFilm;
import modeles.films.Duree;
import modeles.films.Film;
import modeles.films.GenreFilm;
import modeles.films.Pays;
import modeles.films.personne.Personne;
import modeles.images.Affiche;
import modeles.images.Fond;
import modeles.images.Image;

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
	public Film getFilmBase(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film getFilmComplet(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Film> getToutFilms() {
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
	public void supprimerFilm(Film film) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerImage(Image image) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerPersonne(Personne personne) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerDuree(Duree duree) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerGenre(GenreFilm genre) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insererFilm(Film film) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insererPersonne(Personne personne) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insererGenre(GenreFilm genre) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insererDuree(Duree duree) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insererImage(Image image) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insererPays(Pays pays) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Duree> getDurees(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Affiche> getAffichesFilm(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Fond> getFondsFilm(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Personne> getPersonnesFilm(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personne getPersonneBase(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<FichierFilm> getFichiers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerFichierFilm(int idFichier) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<FichierFilm> getFichierFilm(File fichier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerFichierFilm(FichierFilm fichier) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ajouterFichierFilm(FichierFilm fichier) {
		// TODO Auto-generated method stub

	}

}
