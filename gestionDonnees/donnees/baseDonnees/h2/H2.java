package gestionDonnees.donnees.baseDonnees.h2;

import gestionDonnees.donnees.baseDonnees.BaseDonnees;
import gestionDonnees.donnees.baseDonnees.structure.Colonne;
import gestionDonnees.donnees.baseDonnees.structure.Structure;
import gestionDonnees.donnees.baseDonnees.structure.Table;

import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class H2 extends BaseDonnees {

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

	@Deprecated
	public boolean tablesValides() {
		boolean existe = false;
		ArrayList<String> tables = new ArrayList<>();
		try {
			java.sql.PreparedStatement stmt = connection.prepareStatement("SELECT TABLE_NAME FROM "
					+ "INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC' ORDER BY TABLE_NAME");
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

	@Override
	public void mettreEnPlaceTables() {

		String[] tables = { "personnes", "pays", "genres", "films", "fichiers" };
		Table table = new Table(tables[0]);
		Colonne[] colonnes = { new Colonne("id", "int", "UNSIGNED,NOT NULL,AUTO_INCREMENT", true),
				new Colonne("nom", "text", "", false), new Colonne("biographie", "text", "", false) };
		table.ajouterColonnes(colonnes);
		//System.out.println(table.getSql());
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

	@Override
	public Colonne getColonne(String nomColonne) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Structure getStructure() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table getTable(String nomTable) {
		// TODO Auto-generated method stub
		return null;
	}

}
