package org.drfoliberg.films3000.data.cache;

import java.io.File;
import java.util.ArrayList;

import org.drfoliberg.films3000.models.image.Image;


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
