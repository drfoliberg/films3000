package composants.fichiers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;

public class Fichier {
	
	String nom;
	long dateModif;
	
	public Fichier(File fic, String racine) throws IOException{
		this.nom = fic.getAbsolutePath().replace(racine, "");
		this.dateModif = Files.getLastModifiedTime(Paths.get(fic.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS).toMillis();
	}
	
	
	public Fichier(String nom, long dateModif){
		this.nom = nom;
		this.dateModif = dateModif;
	}
	
	public boolean isMod(File fic) throws IOException{
		boolean mod = false;
		FileTime temps =  Files.getLastModifiedTime(Paths.get(fic.getAbsolutePath(),"") , LinkOption.NOFOLLOW_LINKS);
		if(temps.toMillis()!= dateModif){
			mod = true;
		}
		
		return mod;
		
	}

}
