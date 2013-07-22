package composants.fichiers;

public class FichierFilmIncomplet extends Exception {

	/**
*
*/
	private static final long serialVersionUID = 2134674892942481189L;

	public FichierFilmIncomplet(FichierFilm r) {
		super(r.fichierDisque.getAbsolutePath() + " est incomplet");
	}
}