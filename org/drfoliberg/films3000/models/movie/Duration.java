package org.drfoliberg.films3000.models.movie;

public class Duration {

	int dureeMinutes;
	String nomVersion;
	int idFilmTmdb;
	int idVersion;

	/**
	 * 
	 * @param duree
	 *            duree en minutes
	 * @param nom
	 *            nom de la version
	 * @param idFilm
	 *            id du film tmdb
	 * @param idVersion
	 *            numéro de version propre au film
	 */
	public Duration(int duree, String nom, int idFilm, int idVersion) {
		this.dureeMinutes = duree;
		this.nomVersion = nom;
		this.idFilmTmdb = idFilm;
		this.idVersion = idVersion;
	}

	public int getIdFilmTmdb() {
		return idFilmTmdb;
	}

	public void setIdFilmTmdb(int idFilmTmdb) {
		this.idFilmTmdb = idFilmTmdb;
	}

	public int getIdVersion() {
		return idVersion;
	}

	public void setIdVersion(int idVersion) {
		this.idVersion = idVersion;
	}

	public void setDureeMinutes(int dureeMinutes) {
		this.dureeMinutes = dureeMinutes;
	}

	public void setNomVersion(String nomVersion) {
		this.nomVersion = nomVersion;
	}

	public int getIdFilm() {
		return this.idFilmTmdb;
	}

	public int getDureeMinutes() {
		return dureeMinutes;
	}

	public String getNomVersion() {
		return nomVersion;
	}

}
