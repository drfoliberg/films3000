package org.drfoliberg.films3000.modeles.films.personne;

import java.util.ArrayList;

public class Filmographie {

	private ArrayList<InfoFilm> films;
	private int idPersonne;

	public Filmographie(int idPersonne) {
		films = new ArrayList<>();
		this.idPersonne = idPersonne;
	}
	
	public InfoFilm getFilm(int idTmdb){
		InfoFilm film = null;

		for (InfoFilm infoFilm : films) {
			if(infoFilm.getIdFilm() == idTmdb){
				film = infoFilm;
				break;
			}
		}
		
		return film;
	}
	
	public int getNbFilms(){
		return films.size();
	}

	public void ajouterFilm(InfoFilm film) {
		if (film != null) {
			this.films.add(film);
		}
	}

	public int getIdPersonne() {
		return this.idPersonne;
	}

	public ArrayList<InfoFilm> getFilms() {
		return this.films;
	}
}
