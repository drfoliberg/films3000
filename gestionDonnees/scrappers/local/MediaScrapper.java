package gestionDonnees.scrappers.local;

import java.io.File;
import java.util.ArrayList;

import composants.fichiers.Stream;

/**
 * Interface à implémenter pour un service d'analyse des métadonnées des
 * fichiers
 * 
 * @author justin
 * 
 */

public interface MediaScrapper {

	public ArrayList<Stream> getStreams();

	public String getFormat();

	public long getDureeMs();

	public long getTailleOctets();

	public void open(File fichier);

	public void close();
}