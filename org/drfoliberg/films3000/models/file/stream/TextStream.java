package org.drfoliberg.films3000.models.file.stream;

/**
 * 
 * @author justin
 */
public class TextStream extends Stream {

	private String language;

	public TextStream(String language, int streamId) {
		super(streamId);
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("StreamTexte id:");
		sb.append(this.streamId);
		sb.append("\n");
		sb.append(this.language);
		sb.append("\n");
		return sb.toString();
	}
}