package gestionDonnees.donnees.baseDonnees;

import gestionDonnees.donnees.GestionFilm;

public interface BaseDonnees extends GestionFilm {

	public boolean open();
	
	public boolean close();
	
	public String getNom();
	
	public String getUrl();
	
	public void setNom();
	
	public void setUrl();
	
	public boolean tablesExistent();
	
	public void mettreEnPlaceTables();
	
	public boolean baseDonneesExiste();
	
	public void creerBaseDonnees();
	
}
