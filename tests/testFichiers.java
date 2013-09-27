package tests;

import java.io.File;

import org.drfoliberg.films3000.models.file.MovieFile;

public class testFichiers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MovieFile film = new MovieFile(
				new File("/tank/ecole/Films/The.Good.The.Bad.and.The.Ugly[1966][1080p]/The.Good.the.Bad.and.the.Ugly[1966][1080p].mkv"));
		System.out.println(film.getAnnee());
		System.out.println(film.getLength());
		System.out.println(film.getTitre());

	}

}
