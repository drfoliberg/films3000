package gestionDonnees.donnees.baseDonnees.h2;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gestionDonnees.donnees.baseDonnees.BaseDonnees;
import gestionDonnees.donnees.baseDonnees.structure.Colonne;
import gestionDonnees.donnees.baseDonnees.structure.Structure;
import gestionDonnees.donnees.baseDonnees.structure.Table;

public class H2 extends BaseDonnees {

	private String nom;
	private String url;
	private Connection connection;
	//private ModeleBd modele;

	@Override
	public boolean open() {
		try {
			connection = DriverManager.getConnection("jdbc:h2:" + nom, "sa", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean close() {
		try {
			connection.close();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public String getUrl() {
		return this.url;
	}

	@Override
	public void setNom(String nom) {
		this.nom = nom;

	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean tablesValides() {
		boolean existe = false;
		ArrayList<String> tables =  new ArrayList<>();
		try {
			java.sql.PreparedStatement stmt = connection.prepareStatement("SELECT TABLE_NAME FROM " +
					"INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC' ORDER BY TABLE_NAME");
			ResultSet resultats = stmt.executeQuery();
			while (resultats.next()) {
				tables.add(resultats.getString("table_name"));
			}
			resultats.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return existe;
	}
	
	private boolean colonneValide(String colonne){
		
		return false;
	}

	@Override
	public void mettreEnPlaceTables() {

		String [] tables =  {"personnes","pays","genres","films","fichiers"};
		Table table = new Table(tables[0]);
		Colonne [] colonnes = {
				new Colonne("id", "int", new String [] {"UNSIGNED", "NOT NULL", "AUTO_INCREMENT", "PRIMARY"}),
				new Colonne("nom", "text", new String []{"NULL"}),
				new Colonne("biographie", "text", new String [] {"NULL"})
		};
		table.ajouterColonnes(colonnes);
		System.out.println(table.getSql());
	}

	@Override
	public boolean baseDonneesExiste() {
		boolean fichierExiste = true;
		if (!new File(nom + ".h2.db").exists()) {
			fichierExiste = false;
		}
		return fichierExiste;
	}

	@Override
	public void creerBaseDonnees() {
		mettreEnPlaceTables();

	}

}
