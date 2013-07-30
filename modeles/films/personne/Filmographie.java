package modeles.films.personne;

import java.util.ArrayList;

public class Filmographie {

	private ArrayList<InfoFilm> films;
	private int idPersonne;

	public Filmographie(int idPersonne) {
		films = new ArrayList<>();
		this.idPersonne = idPersonne;
	}

	public void ajouterFilm(InfoFilm film) {
		if (film != null) {

		}
	}

	public int getIdPersonne() {
		return this.idPersonne;
	}

	public ArrayList<InfoFilm> getFilms() {
		return this.films;
	}
}
