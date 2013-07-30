package gestionDonnees.scrappers.local.mediainfo;

import java.io.File;
import java.util.ArrayList;

import modeles.fichiers.stream.Stream;
import modeles.fichiers.stream.StreamAudio;
import modeles.fichiers.stream.StreamTexte;
import modeles.fichiers.stream.StreamVideo;

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
			streams.add(getStreamVideo(i));
		}
		for (int i = 0; i < nbAudios; i++) {
			streams.add(getStreamAudio(i));
		}
		for (int i = 0; i < nbTextes; i++) {
			streams.add(getStreamTexte(i));
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
	
	private StreamTexte getStreamTexte(int i){
		String langue = "";
		langue = info.get(StreamKind.Text, i, "Language/String");
		if (langue.equals("")) {
			langue = Constantes.NON_DISPONIBLE;
		}
		return new StreamTexte(langue, i);
	}

	private StreamVideo getStreamVideo(int i) {
		String codec = "";
		int debit = 0;
		String qualite = "";
		int hauteur = 0;
		int largeur = 0;
		double ips = 0;
		int dureeMs = 0;

		//codec
		codec = info.get(StreamKind.Video, i, "Format");
		
		//débit
		if (!info.get(StreamKind.Video, i, "BitRate").equals("")) {
			debit = Integer.parseInt(info.get(StreamKind.Video, i, "BitRate")) / 1000;
		} else if (!info.get(StreamKind.Video, i, "BitRate_Nominal").equals("")) {
			debit = Integer.parseInt(info.get(StreamKind.Video, i,
					"BitRate_Nominal")) / 1000;
		}

		//résolution
		try {
			largeur = Integer.parseInt(info.get(StreamKind.Video, i, "Width"));
			hauteur = Integer.parseInt(info.get(StreamKind.Video, i, "Height"));
		} catch (NumberFormatException e1) {
			System.out.println("Problème avec la résolution du stream vidéo: format invalide");
		}
		
		//images par seconde
		String ipsStr = info.get(StreamKind.Video, i, "FrameRate");
		try {
			ips = Double.parseDouble(ipsStr);
			ips = ((int) (ips * 1000)) / 1000.;
		} catch (NumberFormatException e) {
			System.out.println("Problème avec le nombre d'images par seconde du stream: format invalide");
		}

		if (largeur < 700) {
			qualite = "DVD";
		} else if (largeur < 1900) {
			qualite = "720p";
		} else if (info.get(StreamKind.Video, i, "ScanType").equalsIgnoreCase("Progressive")) {
			qualite = "1080p";
		} else {
			qualite = "1080i";
		}
		
		//durée en ms
		String dureeStr = (info.get(StreamKind.Video, i, "Duration"));
		if (!dureeStr.equals("")) {
			try {
				dureeMs = (int) Double.parseDouble(dureeStr);
			} catch (NumberFormatException e) {
				System.out.println("Problème avec la durée du stream: format invalide");
			}
		}

		return new StreamVideo(codec, debit, qualite, hauteur, largeur, ips, dureeMs, i);
	}

	private StreamAudio getStreamAudio(int i) {
		boolean commentaire = false;
		String canaux = "";
		String codec = "";
		String langue = "";
		int debit = 0;
		int dureeMs = 0;

		// codec audio
		codec = info.get(MediaInfo.StreamKind.Audio, i, "Format");
		if (codec.equalsIgnoreCase("MPEG Audio")) {
			codec = "MP3";
		}

		// débit audio
		String debitStr = info.get(MediaInfo.StreamKind.Audio, i, "BitRate")
				.replaceAll("\\D+", "");

		try {
			debit = (int) Double.parseDouble(debitStr) / 1000;
		} catch (NumberFormatException e) {
			System.out.println("Problème avec le débit audio: format invalide");
		}

		// nombre de canaux
		canaux = info.get(MediaInfo.StreamKind.Audio, i, "Channel(s)");

		// langue du flux audio
		langue = info.get(MediaInfo.StreamKind.Audio, i, "Language/String");

		// détection d'une piste commentaire
		if (info.get(MediaInfo.StreamKind.Audio, i, "Title").toUpperCase().contains("COMMENT") 
				|| info.get(MediaInfo.StreamKind.Audio, i, "Language_More").toUpperCase().contains("COMMENT")) {
			commentaire = true;
		}

		// Duree en ms du flux
		String dureeStr = info.get(StreamKind.Audio, i, "Duration");
		try {
			dureeMs = (int) Double.parseDouble(dureeStr);
		} catch (NumberFormatException e) {
			System.out.println("Problème avec la durée du stream: format invalide");
		}

		return new StreamAudio(codec, debit, canaux, langue, commentaire,
				dureeMs, i);
	}

}
