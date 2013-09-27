package org.drfoliberg.films3000.models.person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.drfoliberg.films3000.data.constants.Constantes;


public class PersonInfo {

	private String name;
	private Date deathday;
	private Date birthday;
	private String image;
	private String bio;
	private int personId;

	public PersonInfo(String name, String deathday, String birthday, String image, String bio, int personId) {
		setBio(bio);
		setImage(image);
		setDeathday(deathday);
		setBirthday(birthday);
		setName(name);
		setPersonId(personId);
	}

	public PersonInfo() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getMort() {
		return this.deathday;
	}

	public void setDeathday(String deathday) {
		java.util.Date date = new Date(0);
		if (deathday != null && !deathday.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(deathday);
			} catch (ParseException e) {
				System.out.println("Erreur avec le format de la date de mort");
			}
		}
		this.deathday = date;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		java.util.Date date = new Date(0);
		if (birthday != null && !birthday.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(birthday);
			} catch (ParseException e) {
				System.out.println("Erreur avec le format de la date de naissance");
			}
		}
		this.birthday = date;

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
			this.bio = Constantes.NOT_AVAILABLE;
		} else {
			this.bio = bio;
		}
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public void setDeathday(Date deathday) {
		this.deathday = deathday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
