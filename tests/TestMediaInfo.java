package tests;

import java.io.File;
import java.util.ArrayList;

import org.drfoliberg.films3000.gestionDonnees.scrappers.local.mediainfo.MediaInfoAnalyse;
import org.drfoliberg.films3000.modeles.fichiers.stream.Stream;


public class TestMediaInfo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MediaInfoAnalyse mediainfo = new MediaInfoAnalyse();
		mediainfo.open(new File("/tank/ecole/Films/The.Good.The.Bad.and.The.Ugly[1966][1080p]/The.Good.the.Bad.and.the.Ugly[1966][1080p].mkv"));
		ArrayList<Stream> streams =  mediainfo.getStreams();
		for (Stream stream : streams) {
			System.out.println(stream.toString());
		}
	}

}
