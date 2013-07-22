package composants.films;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.omertron.themoviedbapi.model.Genre;

public class GenreFilm {
	int id;
	String nom;
	
	public GenreFilm(Genre genre){
		id = genre.getId();
		nom = genre.getName();
	}

	public void enregistrer(Connection con, int idFilm) throws SQLException{
		if(!jointureExiste(con, idFilm)){
			if(!existe(con)){
				inserer(con);
			}
			jointure(con, idFilm);
		}
	}
	
	private void jointure(Connection con, int idFilm) throws SQLException{
		if(!jointureExiste(con,idFilm)){
			java.sql.PreparedStatement pre = con.prepareStatement("Insert " +
					"into genres_has_films (genres_id, films_tmdb_id) values(?,?)");
			pre.setInt(1, id);
			pre.setInt(2,idFilm);
			pre.execute();
		}
	}
	
	private void inserer(Connection con) throws SQLException{
		java.sql.PreparedStatement pre = con.prepareStatement("Insert " +
				"into genres (id, nom) values (?,?)");
		pre.setInt(1,id);
		pre.setString(2,nom);
		pre.execute();
	}
	
	private boolean existe(Connection con) throws SQLException{
		boolean existe = false;
		java.sql.PreparedStatement commande = con.prepareStatement("Select id from genres where id = " +id );
		ResultSet resultat = commande.executeQuery();
		if (resultat.first()) {
			existe = true;
		}
		resultat.close();
		commande.close();
		return existe;
	}
	
	private boolean jointureExiste(Connection con, int idJointure) throws SQLException{
		boolean existe = false;
		java.sql.PreparedStatement commande = con.prepareStatement("Select genres_id from genres_has_films where genres_id = ? and films_tmdb_id = ?" );
		commande.setInt(1, id);
		commande.setInt(2,idJointure);
		ResultSet resultat = commande.executeQuery();
		if (resultat.first()) {
			existe = true;
		}
		resultat.close();
		commande.close();
		return existe;
	}
}
