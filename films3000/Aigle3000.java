package films3000;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import composants.fichiers.Fichier;

public class Aigle3000 {
	
	
	ArrayList<File> fichiersCourants;
	Date dateVer;
	File racine;
	
	public Aigle3000(File racine){
		this.racine = racine;
		fichiersCourants = new ArrayList<>();
	}
	
	
	//String chemin
	//Long modif
	//

	/**
	 * Méthode qui génère la liste des fichiers qui étaient présents lors de la
	 * dernière analyse
	 * @param con Connexion à la bd déjà ouverte
	 * @return Une liste de Fichier avec les dates de modifs
	 * @throws SQLException
	 */
	private ArrayList<Fichier> getFicBd(Connection con) throws SQLException{
		ArrayList<Fichier> fichiersBd = new ArrayList<>();
		String sql = "Select * from fichiers";
		PreparedStatement requete = con.prepareStatement(sql);
		ResultSet resultats = requete.executeQuery();
		
		String nom = "";
		long date = 0;
		
		while(resultats.next()){
			nom = resultats.getString("chemin");
			date = resultats.getLong("modif");
			fichiersBd.add(new Fichier(nom, date));
		}
		
		return fichiersBd;
	}

	public void explorer(File dossier){
		
		File[] fichiers = dossier.listFiles();
		for(File f : fichiers){
			if(f.isFile() && f.length() > Constantes.TAILLE_MIN_FILM){
				fichiersCourants.add(f);
			}else if(f.isDirectory()){
				explorer(f);
			}
		}
	}
	
	public ArrayList<File> getFichiers(){
		explorer(racine);
		
		return fichiersCourants;
	}
	
}
