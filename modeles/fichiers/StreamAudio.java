package modeles.fichiers;

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

	public StreamAudio() {
		this.commentaire = false;
	}

	public StreamAudio(String codec, int debit, String canaux, String langue,
			boolean commentaire, int dureeMs) {
		this.codec = codec;
		this.debit = debit;
		this.canaux = canaux;
		this.langue = langue;
		this.commentaire = commentaire;
		this.dureeMs = dureeMs;
	}

	// @Override
	// public boolean inserer(Connection con, int idFichier) throws SQLException
	// {
	// String sql =
	// "insert into stream_audio (fichiers_id,debit,codec,canaux,langue,commentaire) values (?,?,?,?,?,?)";
	// PreparedStatement commande = con.prepareStatement(sql);
	// commande.setInt(1, idFichier);
	// commande.setInt(2, debit);
	// commande.setString(3, codec);
	// commande.setString(4, canaux);
	// commande.setString(5, langue);
	// commande.setBoolean(6, commentaire);
	// return commande.execute();
	// }

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

}
