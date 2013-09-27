package org.drfoliberg.films3000.models.image;

public class Profile extends Image {
	
	int personId;
	public static final int TYPE = 2;

	public Profile(String chemin, int personId) {
		super(chemin);
		this.personId = personId;
	}
	
	public int getPersonId(){
		return this.personId;
	}

}