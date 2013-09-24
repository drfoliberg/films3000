package org.drfoliberg.films3000.modeles.films;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.omertron.themoviedbapi.model.ProductionCountry;

public class Pays {
	String iso;
	String nom;

	public Pays() {

	}

	public Pays(String iso, String nom) {
		this.iso = iso;
		this.nom = nom;
	}

	@Deprecated
	public Pays(ProductionCountry pays) {
		iso = pays.getIsoCode();
		nom = pays.getName();
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Deprecated
	public void enregistrer(Connection con, int idFilm) throws SQLException {
		if (!existe(con)) {
			inserer(con);
		}
		jointure(con, idFilm);

	}

	@Deprecated
	private void jointure(Connection con, int idFilm) throws SQLException {
		java.sql.PreparedStatement commande = con.prepareStatement("Insert "
				+ "into pays_has_films (pays_id, films_tmdb_id) values(?,?)");
		commande.setString(1, iso);
		commande.setInt(2, idFilm);
		commande.execute();
		commande.close();
	}

	@Deprecated
	private void inserer(Connection con) throws SQLException {
		java.sql.PreparedStatement commande = con.prepareStatement("Insert " + "into pays (iso, nom) values(?,?)");
		commande.setString(1, iso);
		commande.setString(2, nom);
		commande.execute();
		commande.close();
	}

	@Deprecated
	private boolean existe(Connection con) throws SQLException {
		boolean existe = false;
		java.sql.PreparedStatement commande = con.prepareStatement("Select iso from pays where iso = ?");
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
