
package composants.fichiers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mediainfo.MediaInfo;
import mediainfo.MediaInfo.StreamKind;

/**
 * 
 * @author justin
 *
 */
public class StreamAudio extends Stream{

    public String codec;
    public int debit;
    public String canaux;
    public String langue;
    public boolean commentaire;
    public int dureeMs;
    
    /**
     * 
     * @param info le mediainfo du fichier en question déjà ouvert
     * @param noStream le numéro du stream
     */
    
    public StreamAudio(MediaInfo info, int noStream){
    	this.codec = info.get(MediaInfo.StreamKind.Audio, noStream, "Format");
    	
    	String debitBrut = info.get(MediaInfo.StreamKind.Audio, noStream, "BitRate").replaceAll("\\D+", "");
    	if(!debitBrut.equals("")){
    		this.debit = Integer.parseInt(debitBrut)/1000;
    	}
    	this.canaux = info.get(MediaInfo.StreamKind.Audio, noStream, "Channel(s)");
    	this.langue = info.get(MediaInfo.StreamKind.Audio, noStream, "Language/String");
    	if(info.get(MediaInfo.StreamKind.Audio, noStream, "Title").toUpperCase().contains("COMMENT") ||
    			info.get(MediaInfo.StreamKind.Audio, noStream, "Language_More").toUpperCase().contains("COMMENT")){
    		commentaire = true;
    	}
        if(this.codec.equalsIgnoreCase("MPEG Audio")){
            this.codec = "MP3";
        }
		this.dureeMs = (int) Double.parseDouble(info.get(StreamKind.Audio, noStream, "Duration"));
    }

	@Override
	public boolean inserer(Connection con, int idFichier) throws SQLException {
		String sql = "insert into stream_audio (fichiers_id,debit,codec,canaux,langue,commentaire) values (?,?,?,?,?,?)";
		PreparedStatement commande = con.prepareStatement(sql);
		commande.setInt(1, idFichier);
		commande.setInt(2,debit);
		commande.setString(3, codec);
		commande.setString(4, canaux);
		commande.setString(5,langue);
		commande.setBoolean(6, commentaire);
		return commande.execute();
	}
    
}
