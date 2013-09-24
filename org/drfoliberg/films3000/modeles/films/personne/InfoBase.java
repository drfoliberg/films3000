package org.drfoliberg.films3000.modeles.films.personne;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.drfoliberg.films3000.Constantes;


public class InfoBase {

	private String nom;
	private Date mort;
	private Date naissance;
	private String image;
	private String bio;
	private int idPersonne;

	public InfoBase(String nom, String mort, String naissance, String image, String bio, int idPersonne) {
		setBio(bio);
		setImage(image);
		setMort(mort);
		setNaissance(naissance);
		setNom(nom);
		setIdPersonne(idPersonne);
	}

	public InfoBase() {

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getMort() {
		return mort;
	}

	public void setMort(String mort) {
		java.util.Date date = new Date(0);
		if (mort != null && !mort.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(mort);
			} catch (ParseException e) {
				System.out.println("Erreur avec le format de la date de mort");
			}
		}
		this.mort = date;
	}

	public Date getNaissance() {
		return naissance;
	}

	public void setNaissance(String naissance) {
		java.util.Date date = new Date(0);
		if (naissance != null && !naissance.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(naissance);
			} catch (ParseException e) {
				System.out.println("Erreur avec le format de la date de naissance");
			}
		}
		this.naissance = date;

	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		if (bio == null || bio == "") {
			this.bio = Constantes.NON_DISPONIBLE;
		} else {
			this.bio = bio;
		}
	}

	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public void setMort(Date mort) {
		this.mort = mort;
	}

	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}

}
