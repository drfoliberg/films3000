package org.drfoliberg.films3000.modeles.fichiers;

import java.io.File;
import java.util.ArrayList;

import org.drfoliberg.films3000.modeles.fichiers.stream.Stream;


public class Fichier {

	private int id_fichier;
	private int id_api;
	private int duree_fichier;
	private long taille;

	private String format;
	private String nomFichier;
	private File fichierDisque;
	private ArrayList<Stream> streams;

	/**
	 * 
	 * @param id_fichier
	 * @param id_api
	 * @param fichierDisque
	 */
	public Fichier(int id_fichier, int id_api, File fichierDisque) {
		this.id_fichier = id_fichier;
		this.id_api = id_api;
		this.fichierDisque = fichierDisque;
		this.taille = fichierDisque.length();

	}

	/**
	 * 
	 * @param id_fichier
	 * @param id_api
	 * @param duree_fichier
	 * @param taille
	 * @param format
	 * @param nomFichier
	 * @param fichierDisque
	 * @param streams
	 */
	public Fichier(int id_fichier, int id_api, int duree_fichier, long taille, String format, String nomFichier,
			File fichierDisque, ArrayList<Stream> streams) {
		this.id_fichier = id_fichier;
		this.id_api = id_api;
		this.duree_fichier = duree_fichier;
		this.taille = taille;
		this.format = format;
		this.nomFichier = nomFichier;
		this.fichierDisque = fichierDisque;
		this.streams = streams;
	}

	public int getId_fichier() {
		return id_fichier;
	}

	public void setId_fichier(int id_fichier) {
		this.id_fichier = id_fichier;
	}

	public int getId_api() {
		return id_api;
	}

	public void setId_api(int id_api) {
		this.id_api = id_api;
	}

	public int getDuree_fichier() {
		return duree_fichier;
	}

	public void setDuree_fichier(int duree_fichier) {
		this.duree_fichier = duree_fichier;
	}

	public long getTaille() {
		return taille;
	}

	public void setTaille(long taille) {
		this.taille = taille;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public File getFichierDisque() {
		return fichierDisque;
	}

	public void setFichierDisque(File fichierDisque) {
		this.fichierDisque = fichierDisque;
	}

	public ArrayList<Stream> getStreams() {
		return streams;
	}

	public void setStreams(ArrayList<Stream> streams) {
		this.streams = streams;
	}

}
