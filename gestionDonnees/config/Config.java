package gestionDonnees.config;

import org.apache.commons.lang3.ArrayUtils;

import films3000.Constantes;

public class Config implements java.io.Serializable {

	private static final long serialVersionUID = -7423129206895065088L;
	private String clefApi;
	private String langue;
	private boolean touteImages;
	private boolean cacheDisque;
	private String cheminCacheImages;
	private String[] racines;
	private static final String[] languesSupportees = { "fr", "en" };

	/**
	 * 
	 * @param clef
	 *            Clé de l'api tmdb
	 * @param langue
	 *            Langue préférée pour les recherches
	 * @param imageSimple
	 *            ne prendre que l'image par défaut lors de la découverte des
	 *            films. La valeur false va chercher toutes les images
	 *            disponibles à l'aide d'une requête supplémentaire.
	 * 
	 * @param cacheDisque
	 *            activer la cache dynamique sur le disque
	 * 
	 * @param cheminCacheImages
	 *            chemin où enregistrer les images
	 * @param racines
	 *            Les racines (ou la racine) qui contiennent les fichiers à
	 *            analyser
	 */
	public Config(String clef, String langue, boolean touteImages, boolean cacheDisque, String cheminCacheImages,
			String[] racines) {
		this.clefApi = clef;

		if (ArrayUtils.contains(languesSupportees, langue)) {
			this.langue = langue;
		} else {
			this.langue = Constantes.LANGUE_DEFAUT;
		}
		this.touteImages = touteImages;
		this.cacheDisque = cacheDisque;
		this.cheminCacheImages = cheminCacheImages;
		this.racines = racines;
	}

	public boolean getTouteImages() {
		return this.touteImages;
	}

	public String getLangue() {
		return this.langue;
	}

	public String getClefApi() {
		return this.clefApi;
	}

	public boolean isCacheDisque() {
		return cacheDisque;
	}

	public String getCheminCacheImages() {
		return cheminCacheImages;
	}

	public String[] getRacines() {
		return racines;
	}

}
