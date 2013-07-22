package composants.films;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import scrapper.Imdb;

public class Duree {
	
	int dureeMinutes;
	String nomVersion;
	
	
	public Duree(int duree, String nom){
		this.dureeMinutes = duree;
		this.nomVersion = nom;
	}
	
	/**
	 * Méthode qui va chercher sur IMDB les informations de durées et qui les converties en objects durees
	 * @param idImdb L'id du film IMDB et non tmdb !
	 * @return Une liste de durées
	 */
	public static ArrayList<Duree> getDurees(String idImdb){
		String [] dureesString = Imdb.scrapeDurees(idImdb);
		int duree = 0;
		String nom = "";
		Pattern p = Pattern.compile("\\([0-9]{2,3} min\\)");
		Pattern convertirInt = Pattern.compile("[0-9]{2,3}");
		ArrayList<Duree> durees = new ArrayList<>();
		
		for(String s : dureesString){	
			Matcher m = p.matcher(s);
			if(m.find()){
				//duree en minutes
				String dureeStr = s.substring(m.start(),m.end());
				Matcher convertirIntMatcher = convertirInt.matcher(dureeStr);
				convertirIntMatcher.find();
				duree = Integer.parseInt(dureeStr.substring(convertirIntMatcher.start(),convertirIntMatcher.end()));
				//nom
				nom = s.substring(m.end()).trim().replace("(", "").replace(")", "");
				System.out.println(nom + "  " + duree);
				durees.add(new Duree(duree,nom));
			}
		}
		
		
		return durees;
	}
}
