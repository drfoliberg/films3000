package gestionDonnees.donnees.baseDonnees.h2;

import java.util.ArrayList;

import composants.films.Duree;
import composants.films.Film;
import composants.films.GenreFilm;
import composants.films.Pays;
import composants.films.Personne;
import composants.images.Affiche;
import composants.images.Fond;
import composants.images.Image;

import gestionDonnees.donnees.baseDonnees.BaseDonnees;

public class H2 implements BaseDonnees {

	@Override
	public boolean open() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNom() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUrl() {
		// TODO Auto-generated method stub
		
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
	public boolean tablesExistent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mettreEnPlaceTables() {
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
	public boolean baseDonneesExiste() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void creerBaseDonnees() {
		// TODO Auto-generated method stub
		
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
	public void insererImage(Image image) {
		// TODO Auto-generated method stub
		
	}

}
