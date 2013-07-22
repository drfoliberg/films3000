package composants.images;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.omertron.themoviedbapi.model.ArtworkType;

public class Fond extends Image{

	int idFilm;
	
	public Fond(String chemin, int jointure) {
		super(chemin);
		this.idFilm = jointure;
		type = ArtworkType.BACKDROP;
	}
	
	@Override
	public boolean inserer(Connection con) throws SQLException {
		boolean succes = false;
		if(!existe(con,idFilm)){
			String sql = "insert into fonds (chemin, id_tmdb_film) values (?,?)";
			PreparedStatement commande = con.prepareStatement(sql);
			commande.setString(1, chemin);
			commande.setInt(2, idFilm);
			succes = commande.execute();
		}
		
		return succes;
	}
	
	@Override
	protected boolean existe(Connection con, int idFilm) throws SQLException{
		String sql = "Select chemin from fonds where chemin = ? AND id_tmdb_film = ?";
		PreparedStatement commande = con.prepareStatement(sql);
		commande.setString(1, chemin);
		commande.setInt(2,idFilm);
		return commande.executeQuery().first();
		
	}
}
