package org.drfoliberg.films3000.modeles.fichiers.stream;

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
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("StreamTexte id:");
		sb.append(this.idStream);
		sb.append("\n");
		sb.append(this.langue);
		sb.append("\n");
		return sb.toString();
	}
}