package thread;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.omertron.themoviedbapi.MovieDbException;

import composants.fichiers.FichierFilm;
import composants.fichiers.FichierFilmIncomplet;
import composants.films.Film;
import films3000.Aigle3000;
import films3000.Jozin;
import films3000.TraitementEnCours;

public class AnalyseFichiers implements Runnable{
	
	private Jozin jozin;
	private String racine;
	private TraitementEnCours traitement;
	
	public AnalyseFichiers(String racine, Jozin jozin, TraitementEnCours traitement){
		this.jozin = jozin;
		this.racine = racine;
		this.traitement = traitement;
	}

	public void run(){
		
		
		File rac = new File(racine);
		if(!rac.isDirectory()){
			JOptionPane.showMessageDialog(null, "Mauvaise racine de films, je pense");
		}else{
			Aigle3000 aigle = new Aigle3000(new File(racine));
			ArrayList<File> fichiers = aigle.getFichiers();
			traitement.getProgressBar().setIndeterminate(false);
			traitement.setAvancement("Analyse du fichier 0 sur " + fichiers.size());
			traitement.getProgressBar().setMaximum(fichiers.size());
			traitement.getProgressBar().setValue(0);
			
			int nb = 0;
			for(File f : fichiers){
				if(traitement.estAnnule()){
					break;
				}
				traitement.setAvancement("Analyse du fichier " + ++nb + " sur "  + fichiers.size());
				traitement.getProgressBar().setValue(nb);
				traitement.setFic(f.getName());
				FichierFilm ficFilm = jozin.nouveauFichier(f);
				Film[] films = jozin.rechercheFilm(ficFilm);
				if(films.length > 0){
					jozin.lier(ficFilm, films[0].getId());
					try {
						jozin.enregistrerFichier(ficFilm);
					} catch (FichierFilmIncomplet | MovieDbException
							| SQLException e) {
						
						e.printStackTrace();
					}
				}
			}
			if(!traitement.estAnnule()){
				traitement.setAvancement("Analyse Terminée!");
			}
			
		}
	}


}
