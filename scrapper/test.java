package scrapper;

import composants.films.Duree;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//i.scrapeDurees("tt0081505");
		
		for (Duree dur : Imdb.scrapeDurees("tt0081505")) {
			System.out.println(dur.getDureeMinutes() + "   " + dur.getNomVersion());
		}
	}

}
