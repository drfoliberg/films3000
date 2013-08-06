package films3000;

import java.util.Date;

public class Main {


	//private static ArrayList<File> ficCumulatif = new ArrayList<>();

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {	

		//String nomFichier = "Z:/ecole/Films/Matrix.Trilogy[1080p]/The.Matrix[1999][1080p]/The.Matrix[1999][1080p].mkv";
		Jozin coeurLogique =  new Jozin("20d2c5786283461e15f82c94a98cca17", "", new Date(),"en","images");
		gestionDonnees.config.Config conf = new gestionDonnees.config.Config(coeurLogique);
		conf.setVisible(true);
//		FichierFilm f = coeurLogique.nouveauFichier(new File(nomFichier));
//		Film [] films = coeurLogique.rechercheFilm(f);
//		if(films.length>0){
//			coeurLogique.lier(f,films[0].getId());
//			coeurLogique.enregistrerFichier(f);
//		}

//		Jozin coeurLogique =  new Jozin("20d2c5786283461e15f82c94a98cca17", "", new Date(),"en","images");
//		File racine = new File("z:/ecole/films/pixar");
//		File [] fics = racine.listFiles();
//		
//		for (File file : fics) {
//			if(file.isFile()){
//				FichierFilm f = coeurLogique.nouveauFichier(file);
//				Film [] films = coeurLogique.rechercheFilm(f);
//				if(films.length>0){
//					coeurLogique.lier(f,films[0].getId());
//					coeurLogique.enregistrerFichier(f);
//				}
//			}
//		}
		//coeurLogique.close();
	}
	
}
