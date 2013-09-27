package org.drfoliberg.films3000.models.person;

public class PersonMovieInfo {

	String job;
	String special;
	int departement;
	int movieId;
	int personId;

	public PersonMovieInfo() {
		
	}

	public PersonMovieInfo(String job, String special, int departement, int movieId, int personId) {
		this.job = job;
		this.special = special;
		this.departement = departement;
		this.movieId = movieId;
		this.personId = personId;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Personne id:");
		sb.append(this.personId);
		sb.append(" ");
		sb.append(job);
		sb.append(" ");
		sb.append(special);
		sb.append("\n");
		return sb.toString();
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public int getDepartement() {
		return departement;
	}

	public void setDepartement(int departement) {
		this.departement = departement;
	}

	public int getIdFilm() {
		return movieId;
	}

	public void setIdFilm(int idFilm) {
		this.movieId = idFilm;
	}

	public int getIdPersonne() {
		return personId;
	}

	public void setIdPersonne(int idPersonne) {
		this.personId = idPersonne;
	}

}
