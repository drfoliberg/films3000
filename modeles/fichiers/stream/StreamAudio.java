package modeles.fichiers.stream;

/**
 * 
 * @author justin
 * 
 */
public class StreamAudio extends Stream {

	public String codec;
	public int debit;
	public String canaux;
	public String langue;
	public boolean commentaire;
	public int dureeMs;

	/**
	 * 
	 * @param codec
	 * @param debit
	 * @param canaux
	 * @param langue
	 * @param commentaire
	 * @param dureeMs
	 * @param idStream
	 * @param idFichier
	 */
	public StreamAudio(String codec, int debit, String canaux, String langue, boolean commentaire, int dureeMs,
			int idStream) {
		super(idStream);
		this.codec = codec;
		this.debit = debit;
		this.canaux = canaux;
		this.langue = langue;
		this.commentaire = commentaire;
		this.dureeMs = dureeMs;
	}

	public String getCodec() {
		return codec;
	}

	public void setCodec(String codec) {
		this.codec = codec;
	}

	public int getDebit() {
		return debit;
	}

	public void setDebit(int debit) {
		this.debit = debit;
	}

	public String getCanaux() {
		return canaux;
	}

	public void setCanaux(String canaux) {
		this.canaux = canaux;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public boolean isCommentaire() {
		return commentaire;
	}

	public void setCommentaire(boolean commentaire) {
		this.commentaire = commentaire;
	}

	public int getDureeMs() {
		return dureeMs;
	}

	public void setDureeMs(int dureeMs) {
		this.dureeMs = dureeMs;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("StreamAudio id:");
		sb.append(this.idStream);
		sb.append("\n");
		sb.append(this.dureeMs/(1000*60));
		sb.append("min ");
		sb.append(this.codec);
		sb.append(" ");
		sb.append(this.canaux);
		sb.append("ch ");
		sb.append(this.debit);
		sb.append(" kbps");
		sb.append("\n");
		sb.append(this.langue);
		
		if(this.commentaire){
			sb.append(" Commentaire");
		}
		sb.append("\n");
		return sb.toString();
	}
}