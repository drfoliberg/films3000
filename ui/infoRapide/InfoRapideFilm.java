package ui.infoRapide;

import composants.films.Film;

public class InfoRapideFilm extends PanInfoRapide {

	public InfoRapideFilm(Film film) {
		super(film.getTitre(),String.valueOf(film.getAnnee()) ,film.getTitreOrignal(),film.getResume());
	}

}
