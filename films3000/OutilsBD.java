package films3000;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;

import org.h2.tools.RunScript;

import com.omertron.themoviedbapi.model.Genre;


public class OutilsBD {
//	static public void selectTout() throws ClassNotFoundException, SQLException{
//		Class.forName("com.mysql.jdbc.Driver");
//		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost/films3000", "foliberg", "");
//		java.sql.PreparedStatement commande = con.prepareStatement("Select * from Films");
//		ResultSet resultat = commande.executeQuery();
//		while(resultat.next()){
//			System.out.println(resultat.getString("ImdbId") + " "+ resultat.getString("TitreFilm"));
//		}
//	}
	
	public static Connection getCon(String driver, String chemin) throws IOException {
		String bd = "films3000";
		Connection connection = null;
		if(driver.equals("h2")){
			connection = getH2(chemin, bd);
		}else if (driver.equals("mysql")){
			connection = getMySql("", bd);
		}
		return connection;		
	}
	
	public static Connection getMySql(String url, String bd) throws IOException{
		Connection connection = null;
			try {
				String requete = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ? ";
				connection = DriverManager.getConnection("jdbc:mysql://192.168.2.205/films3000","dev","");
				System.out.println("Connexion SQL établie !");
				
				PreparedStatement commande =  connection.prepareStatement(requete);
				commande.setString(1, bd);
				ResultSet r = commande.executeQuery();
				//si la bd n'existe pas on va devoir la créer
				if(!r.first()){
					String ligne ="";
					StringBuilder strb = new StringBuilder();
					FileReader fr = new FileReader("films3000Mysql.sql");
					BufferedReader br = new BufferedReader(fr);
					while((ligne = br.readLine())!=null){
						strb.append(ligne);
					}
					br.close();
					commande = connection.prepareStatement(strb.toString());
					commande.execute();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return connection;	
	}

	public static Connection getH2(String chemin, String bd) throws FileNotFoundException{
		Connection connection = null;
			try {
				boolean fichierExiste = true;
				if (!new File(bd + ".h2.db").exists()) {
					fichierExiste = false;
					System.out.println("La base de données spécifiée n'existe pas");
				}
				connection = DriverManager.getConnection("jdbc:h2:" + chemin , "sa", "");
				System.out.println("Connexion SQL établie !");
				if (!fichierExiste) {
					System.out.println("création des tables!");
					creerTablesH2(connection);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return connection;	
	}
	
	
	private static void creerTablesH2(Connection connection) throws SQLException, FileNotFoundException {
		RunScript.execute(connection, new FileReader("films3000.sql"));
	}


	public boolean genreExiste(Genre genre, Connection con) throws SQLException{
		boolean existe = false;
		java.sql.PreparedStatement commande = con.prepareStatement("Select id from genres where id = " +genre.getId() );
		ResultSet resultat = commande.executeQuery();
		if (resultat.first()) {
			existe = true;
		}
		resultat.close();
		commande.close();
		return existe;
	}
	
}
