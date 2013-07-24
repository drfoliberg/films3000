package ui.tests;

import java.util.EventListener;

import composants.films.Film;

public interface FilmListener extends EventListener {
	void filmSelectionneChange(Film film);
}
