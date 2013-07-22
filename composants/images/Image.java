package composants.images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.model.ArtworkType;

import films3000.Jozin;

public abstract class Image {
	
	protected String chemin;
	protected ArtworkType type;
	
	public Image(String chemin){
		this.chemin = chemin;
	}
	
	protected abstract boolean existe(Connection con, int jointure) throws SQLException;
	
	public abstract boolean inserer(Connection con) throws SQLException;
	
	public boolean estCache(){
		boolean cache = false;
		if(Jozin.cacheActive){
			File img = new File(Jozin.racineImg + chemin);
			if(img.exists()){
				cache = true;
			}
		}
		return cache;
	}
	
	public BufferedImage getImage(TheMovieDbApi api) throws IOException, MovieDbException {

		BufferedImage img = null;

		if (estCache()) {
			img = ImageIO.read(new File(Jozin.racineImg + chemin));
		} else {
			String taille = "";
			if (type.equals(ArtworkType.BACKDROP)) {
				taille = api.getConfiguration().getBackdropSizes().get(0);
			} else if (type.equals(ArtworkType.POSTER)) {
				taille = api.getConfiguration().getPosterSizes().get(0);
			} else {
				taille = api.getConfiguration().getProfileSizes().get(0);
			}
			img = ImageIO.read(api.createImageUrl(chemin, taille));
		}

		return img;
	}
	
	public boolean cacherDisque(BufferedImage img){
		boolean succes = false;
		try {
			ImageIO.write(img, "jpg", new File(Jozin.racineImg + chemin));
		} catch (IOException e) {
			succes = false;
		}
		return succes;
	}
}
