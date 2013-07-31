package modeles.fichiers.stream;

/**
 * 
 * @author justin
 */
public abstract class Stream {

	protected int idStream;
	protected int idFichier;

	public Stream(int idStream) {
		this.idStream = idStream;
	}

	public int getIdStream() {
		return idStream;
	}

	public void setIdStream(int idStream) {
		this.idStream = idStream;
	}

	public int getIdFichier() {
		return idFichier;
	}

	public void setIdFichier(int idFichier) {
		this.idFichier = idFichier;
	}

	
}
