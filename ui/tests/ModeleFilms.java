package ui.tests;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

import composants.films.Film;

import films3000.Jozin;

public class ModeleFilms extends AbstractListModel<Film> {

	private static final long serialVersionUID = 1L;
	private ArrayList<Film> donnees;

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
	

}