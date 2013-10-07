package org.drfoliberg.films3000.data.database.h2;

import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.drfoliberg.films3000.data.database.BaseDonnees;
import org.drfoliberg.films3000.data.database.structure.Colonne;
import org.drfoliberg.films3000.data.database.structure.Structure;
import org.drfoliberg.films3000.data.database.structure.Table;
import org.drfoliberg.films3000.models.file.Snapshot;

public class H2 extends BaseDonnees {

	@Override
	public boolean open() {
		try {
			connection = DriverManager
					.getConnection("jdbc:h2:" + nom, "sa", "");
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
			java.sql.PreparedStatement stmt = connection
					.prepareStatement("SELECT TABLE_NAME FROM "
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

	@Override
	public Snapshot getLastSnapshot() {
		// TODO Auto-generated method stub
		return null;
	}

}
