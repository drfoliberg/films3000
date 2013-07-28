package modeles.films;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.omertron.themoviedbapi.model.ProductionCountry;

public class Pays {
	String iso;
	String nom;
	
	public Pays(ProductionCountry pays){
		iso = pays.getIsoCode();
		nom = pays.getName();
	}

	public void enregistrer(Connection con, int idFilm) throws SQLException{
		if(!existe(con)){
			inserer(con);
		}
		jointure(con, idFilm);

	}
	
	private void jointure(Connection con, int idFilm) throws SQLException{
		java.sql.PreparedStatement commande = con.prepareStatement("Insert " +
				"into pays_has_films (pays_id, films_tmdb_id) values(?,?)");
		commande.setString(1, iso);
		commande.setInt(2, idFilm);
		commande.execute();
		commande.close();
	}
	
	private void inserer(Connection con) throws SQLException{
		java.sql.PreparedStatement commande = con.prepareStatement("Insert " +
				"into pays (iso, nom) values(?,?)");
		commande.setString(1, iso);
		commande.setString(2, nom);
		commande.execute();
		commande.close();
	}
	
	private boolean existe(Connection con) throws SQLException{
		boolean existe = false;
		java.sql.PreparedStatement commande = con.prepareStatement("Select iso from pays where iso = ?" );
		commande.setString(1, iso);
		ResultSet resultat = commande.executeQuery();
		if (resultat.first()) {
			existe = true;
		}
		resultat.close();
		commande.close();
		return existe;
	}
}
