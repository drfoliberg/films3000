package modeles.fichiers;

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

	public StreamVideo() {

	}

	public StreamVideo(String codec, int debit, String qualite, int hauteur,
			int largeur, double ips, int dureeMs) {
		this.codec = codec;
		this.debit = debit;
		this.qualite = qualite;
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.ips = ips;
		this.dureeMs = dureeMs;
	}

	// @Override
	// public boolean inserer(Connection con, int idFichier) throws SQLException
	// {
	// String sql =
	// "insert into stream_video (fichiers_id,codec,debit,qualite,hauteur,largeur,ips) values (?,?,?,?,?,?,?)";
	// PreparedStatement commande = con.prepareStatement(sql);
	// commande.setInt(1, idFichier);
	// commande.setString(2, codec);
	// commande.setInt(3, debit);
	// commande.setString(4, qualite);
	// commande.setInt(5, hauteur);
	// commande.setInt(6, largeur);
	// commande.setString(7, ips);
	// return commande.execute();
	// }

}
