package ui.tests;

import composants.films.Film;

public class FilmEvent {

	private Film film;
	
	public FilmEvent(Film film) {
		this.film =film;
	}
	
	public Film getFilm(){

		return film;
	}
}
