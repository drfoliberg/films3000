package mediainfo;

import java.util.ArrayList;

import mediainfo.MediaInfo.StreamKind;

import composants.fichiers.FichierFilm;
import composants.fichiers.Stream;
import composants.fichiers.StreamAudio;
import composants.fichiers.StreamTexte;
import composants.fichiers.StreamVideo;

import films3000.Constantes;

public class InfosLocales {
	MediaInfo info;
	
	public InfosLocales(){
		info = new MediaInfo();
	}

	private String getFormat(){
		String format = info.get(MediaInfo.StreamKind.General, 0, "Format");
		if(format.equals("")){
			return Constantes.NON_DISPONIBLE;
		}
		return format;
	}
	
	private long getTaille(){
		String taille = info.get(MediaInfo.StreamKind.General, 0, "FileSize");
		if(taille.equals("")){
			return 0;
		}
		return Long.parseLong(taille);
	}
	
	private int getDuree(){
		int duree= 0;
		String tmp = info.get(MediaInfo.StreamKind.General, 0, "Duration");
		if(!tmp.equals("")){
			duree = (int) Double.parseDouble(tmp);
		}
		return duree;
	}
	
	public void getInfosFichier(FichierFilm fichier){
		info.open(fichier.fichierDisque);
		fichier.setStreams(analyseStreams());
		fichier.taille = getTaille();
		fichier.format = getFormat();
		fichier.duree = getDuree();
		info.close();
	}
	
	private ArrayList<Stream> analyseStreams(){
		
		ArrayList<Stream> streams = new ArrayList<>();
		
		int nbVideos= 0;
		int nbAudios = 0;
		int nbTextes = 0;
		//ne pas utiliser info.streamCount(streamKind) car problÃ¨mes avec Linux 
		String tmpVideo = info.get(StreamKind.Video,0, "StreamCount");
		if(!tmpVideo.equals("")){
			nbVideos = Integer.parseInt(tmpVideo);
		}
		
		String tmpAudios = info.get(StreamKind.Audio,0, "StreamCount");
		if(!tmpAudios.equals("")){
			nbAudios = Integer.parseInt(tmpAudios);
		}

		String tmpTextes = info.get(StreamKind.Text,0, "StreamCount");
		if(!tmpTextes.equals("")){
			nbTextes = Integer.parseInt(tmpTextes);
		}

		for (int i = 0; i <nbVideos; i++) {
			StreamVideo stream = new StreamVideo(info,i);
			streams.add(stream);
		}
		for(int i= 0; i < nbAudios;i++){
			StreamAudio stream = new StreamAudio(info, i);
			streams.add(stream);
		}
		for (int i = 0; i < nbTextes; i++) {
			StreamTexte stream = new StreamTexte(info, i);
			streams.add(stream);
		}
		
		return streams;
	}
}
