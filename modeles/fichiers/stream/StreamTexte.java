package modeles.fichiers.stream;

/**
 * 
 * @author justin
 */
public class StreamTexte extends Stream {

	private String langue;

	public StreamTexte(String langue, int idStream) {
		super(idStream);
		this.langue = langue;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}
}
