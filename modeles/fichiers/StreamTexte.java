package modeles.fichiers;

import films3000.Constantes;
import gestionDonnees.scrappers.local.mediainfo.MediaInfo;
import gestionDonnees.scrappers.local.mediainfo.MediaInfo.StreamKind;

/**
 * 
 * @author justin
 */
public class StreamTexte extends Stream {

	private String langue;
	private int idFichier;

	// public int taille;

	public StreamTexte(String langue) {
		this.langue = langue;
	}

	public StreamTexte(MediaInfo info, int noStream) {
		this.type = StreamKind.Text;
		langue = info.get(type, noStream, "Language/String");
		if (langue.equals("")) {
			langue = Constantes.NON_DISPONIBLE;
		}
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public int getIdFichier() {
		return idFichier;
	}

	public void setIdFichier(int idFichier) {
		this.idFichier = idFichier;
	}
}
