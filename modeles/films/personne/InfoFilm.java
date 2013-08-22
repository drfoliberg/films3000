package modeles.films.personne;

public class InfoFilm {

	String job;
	String special;
	int departement;
	int idFilm;
	int idPersonne;

	public InfoFilm() {
		
	}

	public InfoFilm(String job, String special, int departement, int idFilm, int idPersonne) {
		this.job = job;
		this.special = special;
		this.departement = departement;
		this.idFilm = idFilm;
		this.idPersonne = idPersonne;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Personne id:");
		sb.append(this.idPersonne);
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
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

}
