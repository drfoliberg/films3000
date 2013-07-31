package modeles.films.personne;

public class Personne {

	private InfoBase infoBase;
	private Filmographie filmographie;

	public Personne(int idPersonne) {
		this.filmographie = new Filmographie(idPersonne);
		this.infoBase = new InfoBase();
	}

	public Filmographie getFilmograhie() {
		return this.filmographie;
	}

	public InfoBase getInfoBase() {
		return infoBase;
	}

	public void setInfoBase(InfoBase infoBase) {
		this.infoBase = infoBase;
	}
}
