package org.drfoliberg.films3000.modeles.fichiers;

import java.io.File;

public class RenommeurTvTheorique extends Renommeur {

	private int saison;
	private int episode;
	private String serie;
	
	public RenommeurTvTheorique(File f) {
		super(f);
	}

	@Override
	protected void trouverParams() {
		nettoyerEspaces();
		trouverSaison();
		trouverSerie();
		trouverEpisode();
	}

	private void trouverEpisode() {
		this.episode = 0;
		
	}

	private void trouverSaison() {
		this.saison = 2;
		
	}

	private void trouverSerie() {
		this.serie = "Simpsons";
		
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
