package gestionDonnees.cache;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import modeles.images.Image;

public class CacheImages {

	private String cheminCache;
	private ArrayList<Image> images;
	File racine;

	public CacheImages(String cheminCache) {
		this.cheminCache = cheminCache;
		this.racine = new File(cheminCache);
		initialiser();
	}

	public boolean ajouterImage(Image image) {

		return false;

	}

	public boolean estCache(Image image) {
		return false;
	}

	private boolean initialiser() {
		if (this.racine.exists() && this.racine.isDirectory()) {
			images = new ArrayList<>();
			File[] fichiers = racine.listFiles();
			for (int i = 0; i < fichiers.length; i++) {
				File file = fichiers[i];
				//images.add();
			}
		} else {
			return false;
		}
		return true;
	}

}
