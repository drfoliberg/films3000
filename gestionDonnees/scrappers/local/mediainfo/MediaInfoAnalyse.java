package gestionDonnees.scrappers.local.mediainfo;

import java.io.File;
import java.util.ArrayList;

import composants.fichiers.Stream;
import composants.fichiers.StreamAudio;
import composants.fichiers.StreamTexte;
import composants.fichiers.StreamVideo;

import films3000.Constantes;
import gestionDonnees.scrappers.local.MediaScrapper;
import gestionDonnees.scrappers.local.mediainfo.MediaInfo.StreamKind;

public class MediaInfoAnalyse implements MediaScrapper {
	MediaInfo info;

	public MediaInfoAnalyse() {
		info = new MediaInfo();
	}

	public String getFormat() {
		String format = info.get(MediaInfo.StreamKind.General, 0, "Format");
		if (format.equals("")) {
			return Constantes.NON_DISPONIBLE;
		}
		return format;
	}

	public long getTailleOctets() {
		String taille = info.get(MediaInfo.StreamKind.General, 0, "FileSize");
		if (taille.equals("")) {
			return 0;
		}
		return Long.parseLong(taille);
	}

	public long getDureeMs() {
		long duree = 0;
		String tmp = info.get(MediaInfo.StreamKind.General, 0, "Duration");
		if (!tmp.equals("")) {
			duree = (long) Double.parseDouble(tmp);
		}
		return duree;
	}

	@Override
	public ArrayList<Stream> getStreams() {
		ArrayList<Stream> streams = new ArrayList<>();

		int nbVideos = 0;
		int nbAudios = 0;
		int nbTextes = 0;
		// ne pas utiliser info.streamCount(streamKind) car problèmes avec
		// Linux
		String tmpVideo = info.get(StreamKind.Video, 0, "StreamCount");
		if (!tmpVideo.equals("")) {
			nbVideos = Integer.parseInt(tmpVideo);
		}

		String tmpAudios = info.get(StreamKind.Audio, 0, "StreamCount");
		if (!tmpAudios.equals("")) {
			nbAudios = Integer.parseInt(tmpAudios);
		}

		String tmpTextes = info.get(StreamKind.Text, 0, "StreamCount");
		if (!tmpTextes.equals("")) {
			nbTextes = Integer.parseInt(tmpTextes);
		}

		for (int i = 0; i < nbVideos; i++) {
			StreamVideo stream = new StreamVideo(info, i);
			streams.add(stream);
		}
		for (int i = 0; i < nbAudios; i++) {
			StreamAudio stream = new StreamAudio(info, i);
			streams.add(stream);
		}
		for (int i = 0; i < nbTextes; i++) {
			StreamTexte stream = new StreamTexte(info, i);
			streams.add(stream);
		}

		return streams;
	}

	@Override
	public void open(File fichier) {
		info.open(fichier);
	}

	@Override
	public void close() {
		info.close();
	}

}
