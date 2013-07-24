
package composants.fichiers;

import java.sql.Connection;
import java.sql.SQLException;

import mediainfo.MediaInfo.StreamKind;

/**
 *
 * @author justin
 */
public abstract class Stream {
    
    int id;
    StreamKind type;
    
    public Stream(){
        
    }
    
    public abstract boolean inserer(Connection con, int idFichier) throws SQLException;

    
}
