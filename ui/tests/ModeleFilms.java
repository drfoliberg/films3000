package ui.tests;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.event.EventListenerList;

import composants.films.Film;

import films3000.Jozin;

public class ModeleFilms extends AbstractListModel<Film> {

	private static final long serialVersionUID = 1L;
	private ArrayList<Film> donnees;
	private final EventListenerList listeners = new EventListenerList();
	private Film film;

	public ModeleFilms(Jozin jozin) throws SQLException {
		donnees = initialiserFilms(jozin);
	}

	private ArrayList<Film> initialiserFilms(Jozin jozin) throws SQLException {
		ArrayList<Film> listeFilms = new ArrayList<Film>();
		listeFilms = jozin.getTousFilms();
		return listeFilms;
	}

	@Override
	public Film getElementAt(int noLigne) {
		return donnees.get(noLigne);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return donnees.size();
	}
	
	public Film getFilm() {
		return this.film;
	}

	public void setId(Film film) {
		this.film = film;
		fireFilmChange(film);
	}

	public FilmListener[] getFilmListeners() {
		return listeners.getListeners(FilmListener.class);
	}
	
    public void addFilmListener(FilmListener listener) {
        listeners.add(FilmListener.class, listener);
    }

	protected void fireFilmChange(Film film) {
		FilmEvent event = null;
		for (FilmListener listener : getFilmListeners()) {
			if(event==null){
				event = new FilmEvent(film);
			}
			listener.filmSelectionneChange(film);
		}
	}
	

}