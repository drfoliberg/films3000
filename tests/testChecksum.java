package tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.drfoliberg.films3000.Constantes;
import org.drfoliberg.films3000.modeles.fichiers.FichierFilm;

public class testChecksum {

	/**
	 * @param args
	 * @throws IOException
	 */
	static ArrayList<File> fichiersCourants;

	public static void main(String[] args) throws IOException {
		compareFiles();
	}
	public static void compareFiles(){
		File f = new File("/tank/ecole/Films/12.Angry.Men[1957][1080p]/12.Angry.Men[1957][1080p].mkv");
		File f2 = new File("/tank/ecole/Films/12.Monkeys[1995]/12.Monkeys[1995].mkv");
		FichierFilm ff1 = new FichierFilm(f);
		FichierFilm ff2 = new FichierFilm(f);
		FichierFilm ff3 = new FichierFilm(f2);
		System.out.println("1 et 2:" + ff1.equals(ff2));
		System.out.println("2 et 3:" + ff2.equals(ff3));
	}
	
	public static void getAllHashes() throws IOException{
		ArrayList<Long> hashes = new ArrayList<>();
		fichiersCourants = new ArrayList<>();
		testChecksum.explorer(new File("/tank/ecole/Films/"));
		for (File f : fichiersCourants) {
			FichierFilm ficFilm = new FichierFilm(f);
			long h = ficFilm.getCheckSum();
			hashes.add(h);
			System.out.println(ficFilm.getTitre() + " " + ficFilm.getPath() + " " + h);
		
		}
		System.out.println();
	}

	public static void explorer(File dossier) {

		File[] fichiers = dossier.listFiles();
		for (File f : fichiers) {
			if (f.isFile() && f.length() > Constantes.TAILLE_MIN_FILM) {
				fichiersCourants.add(f);
			} else if (f.isDirectory()) {
				explorer(f);
			}
		}
	}

	public ArrayList<File> getFichiers(File racine) {
		explorer(racine);

		return fichiersCourants;
	}

}
