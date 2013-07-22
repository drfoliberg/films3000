package composants.images;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.omertron.themoviedbapi.model.ArtworkType;

public class Profil extends Image{
	int idPersonne;
	
	public Profil(String chemin, int jointure) {
		super(chemin);
		this.idPersonne = jointure;
		type = ArtworkType.PROFILE;
	}
	
	@Override
	public boolean inserer(Connection con) throws SQLException {
		boolean succes = false;
		if(!existe(con,idPersonne)){
			String sql = "insert into profils (chemin, id_personne) values (?,?)";
			PreparedStatement commande = con.prepareStatement(sql);
			commande.setString(1, chemin);
			commande.setInt(2, idPersonne);
			succes = commande.execute();
		}
		
		return succes;
	}
	
	@Override
	protected boolean existe(Connection con, int idPersonne) throws SQLException{
		String sql = "Select chemin from profils where chemin = ? AND id_personne = ?";
		PreparedStatement commande = con.prepareStatement(sql);
		commande.setString(1, chemin);
		commande.setInt(2,idPersonne);
		return commande.executeQuery().first();
		
	}
}
