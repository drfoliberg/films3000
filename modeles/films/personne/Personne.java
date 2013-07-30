package modeles.films.personne;

import java.util.ArrayList;

import com.omertron.themoviedbapi.model.Person;

public class Personne {

	private InfoBase infoBase;
	private ArrayList<InfoFilm> filmographie;

	public Personne(int id) {

	}

	public Personne(Person infoPersonne) {

	}

	public int getId() {
		return infoBase.getIdPersonne();
	}

	public String getNom() {
		return infoBase.getNom();
	}

	public String getMort() {
		// FIXME
		return infoBase.getMort().toString();
	}

	public String getNaissance() {
		// FIXME
		return infoBase.getNaissance().toString();
	}

	public String getProfilePath() {
		return infoBase.getImage();
	}

	public String getBio() {
		return infoBase.getBio();
	}

	public void setId(int id) {
		this.infoBase.setIdPersonne(id);
	}

	public void setNom(String nom) {
		this.infoBase.setNom(nom);
	}

	public void setMort(String mort) {
		this.infoBase.setMort(mort);
	}

	public void setNaissance(String naissance) {
		this.infoBase.setNaissance(naissance);
	}

	public void setImage(String image) {
		this.infoBase.setImage(image);
	}

	public void setBio(String bio) {
		this.infoBase.setBio(bio);
	}

}
