
package composants.fichiers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import films3000.Constantes;
import gestionDonnees.scrappers.local.mediainfo.MediaInfo;
import gestionDonnees.scrappers.local.mediainfo.MediaInfo.StreamKind;

/**
 *
 * @author justin
 */
public class StreamTexte extends Stream{
    
    public String langue;
    //public int taille;
    
    public StreamTexte(MediaInfo info, int noStream){
    	this.type = StreamKind.Text;
    	langue = info.get(type, noStream, "Language/String");
    	if(langue.equals("") ) {
    		langue = Constantes.NON_DISPONIBLE;
    	}
//    	String tmp = info.get(type, noStream, "StreamSize");
//    	taille = (int) Double.parseDouble(tmp);
    }
	@Override
	public boolean inserer(Connection con, int idFichier) throws SQLException {
		String sql = "insert into stream_texte (fichiers_id, langue) values (?,?)";
		PreparedStatement commande = con.prepareStatement(sql);
		commande.setInt(1, idFichier);
		commande.setString(2, langue);
		return commande.execute();
	}
    
}
	

