package modeles.fichiers;

import java.io.File;
import java.util.ArrayList;

import modeles.fichiers.stream.Stream;

public class FichierTVThorique extends Fichier {

	private int saison;
	private int episode;
	private String serie;

	public FichierTVThorique(int id_fichier, int id_api, File fichierDisque) {
		super(id_fichier, id_api, fichierDisque);
		RenommeurTvTheorique renommeur = new RenommeurTvTheorique(fichierDisque);
		this.saison = renommeur.getSaison();
		this.episode = renommeur.getEpisode();
		this.serie = renommeur.getSerie();
	}

	public FichierTVThorique(int id_fichier, int id_api, int duree_fichier, long taille, String format,
			String nomFichier, File fichierDisque, ArrayList<Stream> streams, int saison, int episode, String serie) {
		super(id_fichier, id_api, duree_fichier, taille, format, nomFichier, fichierDisque, streams);
		this.saison = saison;
		this.episode = episode;
		this.serie = serie;
	}

	public int getSaison() {
		return saison;
	}

	public void setSaison(int saison) {
		this.saison = saison;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

}
