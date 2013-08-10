package modeles.images;

public class Profil extends Image {
	
	int idPersonne;
	public static final int TYPE = 2;

	public Profil(String chemin, int idPersonne) {
		super(chemin);
		this.idPersonne = idPersonne;
	}
	
	public int getIdPersonne(){
		return this.idPersonne;
	}

}