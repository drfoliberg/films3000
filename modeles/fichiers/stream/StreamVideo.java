package modeles.fichiers.stream;

/**
 * 
 * @author justin
 */
public class StreamVideo extends Stream {

	public String codec;
	public int debit;
	public String qualite;
	public int hauteur;
	public int largeur;
	public double ips;
	public int dureeMs;

	/**
	 * 
	 * @param codec
	 * @param debit
	 * @param qualite
	 * @param hauteur
	 * @param largeur
	 * @param ips
	 * @param dureeMs
	 * @param idStream
	 * @param idFichier
	 */
	public StreamVideo(String codec, int debit, String qualite, int hauteur, int largeur, double ips, int dureeMs,
			int idStream) {
		super(idStream);
		this.codec = codec;
		this.debit = debit;
		this.qualite = qualite;
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.ips = ips;
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
		sb.append(this.ips);
		sb.append("fps ");
		sb.append(this.debit);
		sb.append(" kbps ");
		sb.append(this.qualite);

		sb.append("\n");
		return sb.toString();
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

	public String getQualite() {
		return qualite;
	}

	public void setQualite(String qualite) {
		this.qualite = qualite;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public double getIps() {
		return ips;
	}

	public void setIps(double ips) {
		this.ips = ips;
	}

	public int getDureeMs() {
		return dureeMs;
	}

	public void setDureeMs(int dureeMs) {
		this.dureeMs = dureeMs;
	}

}
