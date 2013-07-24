package composants.films;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.model.Person;
import composants.images.Profil;

import films3000.Constantes;
import films3000.Departements;

public class Personne {

	//Commun
	int id;
	String nom;
	//Base
	String mort;
	String naissance;
	String image;
	String bio;
	//Film
	String job;
	String special;
	int departement;
	boolean conserver;
	

	public Personne(int id){
		this.id = id;
	}
	
	public Personne(Person infoPersonne){
		this.id = infoPersonne.getId();
	}

	public void setInfosBase(TheMovieDbApi api) throws MovieDbException {
		Person infoBase = api.getPersonInfo(getId());
		nom = infoBase.getName();
		naissance = infoBase.getBirthday();
		mort = infoBase.getDeathday();
		bio = infoBase.getBiography();
		if (bio == null) {
			bio = Constantes.NON_DISPONIBLE;
		}
		image = infoBase.getProfilePath();
		if(image==null){
			image = Constantes.NON_DISPONIBLE;
		}
	}
	
	public void setInfosFilm(Person infoFilm) {
		special = "";
		conserver = false;
		nom = infoFilm.getName();
		job = infoFilm.getJob();
		switch (infoFilm.getDepartment()) {
		case ("acting"):
			departement = 2;
			conserver =true;
			job = infoFilm.getCharacter();
			if (!infoFilm.getJob().equals("actor")) {
				special = infoFilm.getJob();
			}
			break;
		case ("Writing"):
			departement = Departements.WRITING.getNoDep();
			if(Departements.WRITING.getJobsConserver().contains(job)){
				job = infoFilm.getJob();
				conserver = true;
			}
			break;
		case ("Directing"):
			departement = Departements.DIRECTING.getNoDep();
			if(Departements.DIRECTING.getJobsConserver().contains(job)){
				job = infoFilm.getJob();
				conserver = true;
			}
			break;
		case ("Camera"):
			departement = Departements.CAMERA.getNoDep();
			if(Departements.CAMERA.getJobsConserver().contains(job)){
				job = infoFilm.getJob();
				conserver = true;
			}
			break;
		case ("Editing"):
			departement = Departements.EDITING.getNoDep();
			if(Departements.EDITING.getJobsConserver().contains(job)){
				job = infoFilm.getJob();
				conserver = true;
			}
			break;
		case ("Production"):
			departement = Departements.PRODUCTION.getNoDep();
			if(Departements.PRODUCTION.getJobsConserver().contains(job)){
				job = infoFilm.getJob();
				conserver = true;
			}
			break;
		case ("Sound"):
			departement = Departements.SOUND.getNoDep();
			if(Departements.SOUND.getJobsConserver().contains(job)){
				job = infoFilm.getJob();
				conserver = true;
			}
			break;
		}
		if(job==null){
			job = Constantes.NON_DISPONIBLE;
		}
	}
	

	/**
	 * Méthode qui insère la personne courante pour un film. La méthode se charge dappeler 
	 * la recherche et linsertion de la personne de base si elle nexiste pas.
	 * 
	 * @param idFilm Le film duquel la personne fait partie
	 * @param api Lapi qui pourrait être utilisé pour la recherche dinformations de base si 
	 * @param con la connection sql déjà ouverte
	 * @return sil y a eu une erreur
	 * 
	 * @throws MovieDbException
	 * @throws SQLException 
	 */
	public boolean insererPersonneFilm(int idFilm, TheMovieDbApi api, Connection con) throws MovieDbException, SQLException{
		boolean erreur = false;
		
		if(conserver && !jointureExiste(con, idFilm)){
			
			if(!baseExiste(con)){
				Personne pBase = new Personne(api.getPersonInfo(id));
				pBase.setInfosBase(api);
				pBase.insererInfosBase(con);
				//System.out.println(nom + " de base insérée!");
			}
			
			String sql = "Insert into Personnes_films (Personnes_id, films_tmdb_id,special,job,departement) " +
					"values(?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setInt(2, idFilm);
			stmt.setString(3,special);
			stmt.setString(4, job);
			stmt.setInt(5, departement);
			erreur = stmt.execute();
			//System.out.println(nom + " de " + job + "inséré!");
			}
		return erreur;
	}

	/**
	 * Méthode qui se charge de linsertion dune personne de base. Cet enregistrement contient les
	 * informations tels que la bio et limage de la personne.
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public boolean insererInfosBase(Connection con) throws SQLException{
		Profil profil =new Profil(image, id);
		profil.inserer(con);
		
		PreparedStatement stmt = con.prepareStatement("Insert into personnes (id,nom,biographie) Values (?, ?, ?)");
		stmt.setInt(1, id);
		stmt.setString(2, nom);
		stmt.setString(3, bio);
		return stmt.execute();
	}
	
	private boolean baseExiste(Connection con) throws SQLException{
		String sql = "Select id  from personnes where id = " + id;
		ResultSet r = con.prepareStatement(sql).executeQuery();
		return r.first();
	}
	
	private boolean jointureExiste(Connection con, int idJointure) throws SQLException{
		String sql = "Select Personnes_id from Personnes_films where Personnes_id = ? and films_tmdb_id = ? and job = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.setInt(2, idJointure);
		stmt.setString(3, job);
		return stmt.executeQuery().first();
	}
	
	public int getId() {
		return id;
	}
	
	
	

	public boolean isConserver() {
		return conserver;
	}

	public String getJob() {
		return job;
	}

	public String getNom() {
		return nom;
	}

	public String getMort() {
		return mort;
	}

	public String getNaissance() {
		return naissance;
	}

	public String getProfilePath() {
		return image;
	}

	public String getBio() {
		return bio;
	}

	public String getSpecial() {
		return special;
	}

	public int getDepartement() {
		return departement;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setMort(String mort) {
		this.mort = mort;
	}

	public void setNaissance(String naissance) {
		this.naissance = naissance;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public void setDepartement(int departement) {
		this.departement = departement;
	}

	public void setConserver(boolean conserver) {
		this.conserver = conserver;
	}
	
}
