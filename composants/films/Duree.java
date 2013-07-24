package composants.films;

public class Duree {
	
	int dureeMinutes;
	String nomVersion;
	
	
	public Duree(int duree, String nom){
		this.dureeMinutes = duree;
		this.nomVersion = nom;
	}

	public int getDureeMinutes() {
		return dureeMinutes;
	}

	public String getNomVersion() {
		return nomVersion;
	}
	
}
