
package modeles.fichiers;

import gestionDonnees.scrappers.local.mediainfo.MediaInfo.StreamKind;

import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author justin
 */
public abstract class Stream {
    
    int id;
    StreamKind type;
    
    public Stream(){
        
    }
    
    //public abstract boolean inserer(Connection con, int idFichier) throws SQLException;

    
}
