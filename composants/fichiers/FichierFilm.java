package composants.fichiers;

import java.io.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mediainfo.Renomeur;


/**
 *
 * @author justin
 */
public class FichierFilm {
    
	private int id_fichier;
	public int id_tmdb;
    public int duree;
    public long taille;

    public String format;
    public String nomFichier;
    public File fichierDisque;
    public ArrayList<Stream> streams = null;
    public String titre;
    public int annee;

    public FichierFilm(File fichier){
    	this.fichierDisque = fichier;
    	nomFichier = fichier.getPath().replaceAll("\\\\","/");
    }
    
    public void analyserTitreDate(){
    	Renomeur r = new Renomeur(fichierDisque);
    	titre = r.getTitre();
    	annee = r.getAnnee();
    }
    
    public void setStreams(ArrayList<Stream> streams){
    	this.streams = streams;
    }
    
    public boolean inserer(Connection con) throws SQLException{
    	java.sql.PreparedStatement commande = con.prepareStatement("Insert into fichiers (films_tmdb_id, taille, nom_fichier, duree, format) " +
					"values(?,?,?,?,?)");
    	commande.setInt(1, id_tmdb);
    	commande.setLong(2, taille);
    	commande.setString(3, nomFichier);
    	commande.setInt(4, duree);
    	commande.setString(5, format);
    	this.id_fichier = getIdEnregistrement(con);
    	return commande.execute();
    }
    
    public boolean existe(Connection con) throws SQLException{
		boolean existe = false;
		java.sql.PreparedStatement commande = con.prepareStatement("Select nom_fichier from fichiers where nom_fichier =  ?" );
		commande.setString(1,nomFichier);
		ResultSet resultat = commande.executeQuery();
		if (resultat.first()) {
			existe = true;
		}
		resultat.close();
		commande.close();
		return existe;
    }
    
    public int getIdEnregistrement(Connection con) throws SQLException{
    	int id = -1;
    	String sql = "Select id from fichiers where nom_fichier = ?";
    	PreparedStatement commande =  con.prepareStatement(sql);
    	commande.setString(1, nomFichier);
    	ResultSet r = commande.executeQuery();
    	if(r.first()){
    		id = r.getInt(1);
    	}
    	return id;
    }
    
    public void insererStreams(Connection con) throws SQLException{
    	if(id_fichier == -1){
    		id_fichier = getIdEnregistrement(con);
    	}
    	
    	for(Stream s : streams){
    		s.inserer(con, id_fichier);
    	}
    	
    }
    
	public int getId_fichier() {
		return id_fichier;
	}
}