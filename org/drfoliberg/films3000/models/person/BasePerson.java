package org.drfoliberg.films3000.models.person;

import org.drfoliberg.films3000.managers.Filmography;

public class BasePerson {

	private PersonInfo baseInfo;
	private Filmography filmography;

	public BasePerson(int personId) {
		this.filmography = new Filmography(personId);
		this.baseInfo = new PersonInfo();
	}

	public Filmography getFilmograhie() {
		return this.filmography;
	}

	public PersonInfo getInfoBase() {
		return baseInfo;
	}

	public void setInfoBase(PersonInfo baseInfo) {
		this.baseInfo = baseInfo;
	}
}
