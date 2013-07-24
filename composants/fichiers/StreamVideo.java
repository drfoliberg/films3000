
package composants.fichiers;

import gestionDonnees.scrappers.local.mediainfo.MediaInfo;
import gestionDonnees.scrappers.local.mediainfo.MediaInfo.StreamKind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



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
    public String ips;
    public int dureeMs;
    
    
    public StreamVideo(MediaInfo info, int noStream){
    	this.type = StreamKind.Video;
    	this.codec=info.get(type, noStream, "Format");
    	if(!info.get(type, noStream, "BitRate").equals("")){
    		this.debit = Integer.parseInt(info.get(type, noStream, "BitRate"))/1000;
    	}else if(!info.get(type, noStream, "BitRate_Nominal").equals("")){
    		this.debit = Integer.parseInt(info.get(type, noStream, "BitRate_Nominal"))/1000;
    	}
    	
    	this.largeur=Integer.parseInt(info.get(type, noStream, "Width"));
    	this.hauteur = Integer.parseInt(info.get(type, noStream, "Height"));
    	this.ips = info.get(type, noStream, "FrameRate/String");
    	
        if (largeur < 700){
            qualite = "DVD";
        }else if (largeur < 1700){
            qualite = "720p";
        }else if(info.get(type, noStream, "ScanType").equalsIgnoreCase("Progressive")){
            qualite = "1080p";
        }else{
            qualite = "1080i";
        }
        String tmpTemps = (info.get(type, noStream, "Duration"));
        if(!tmpTemps.equals("")){
        	this.dureeMs = (int) Double.parseDouble(tmpTemps);
        }
    }


	@Override
	public boolean inserer(Connection con, int idFichier) throws SQLException {
		String sql = "insert into stream_video (fichiers_id,codec,debit,qualite,hauteur,largeur,ips) values (?,?,?,?,?,?,?)";
		PreparedStatement commande = con.prepareStatement(sql);
		commande.setInt(1, idFichier);
		commande.setString(2,codec);
		commande.setInt(3, debit);
		commande.setString(4,qualite);
		commande.setInt(5, hauteur);
		commande.setInt(6, largeur);
		commande.setString(7,ips);
		return commande.execute();
	}
    
}
