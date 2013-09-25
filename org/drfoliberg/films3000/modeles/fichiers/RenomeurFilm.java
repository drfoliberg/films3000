package org.drfoliberg.films3000.modeles.fichiers;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Object renomeur
 * 
 * @author justin
 * 
 */

public class RenomeurFilm extends Renommeur{

	String titre;
	String date;
	private boolean dateTrouve;
	int indexDate;
	int indexQualite;
	static Pattern pDateC = Pattern.compile("\\[[0-9]{4}\\]");
	static Pattern pDateD = Pattern.compile("\\.[0-9]{4}\\.");

	public RenomeurFilm(File f) {
		super(f);
		trouverParams();
	}


	public String getNomFic() {
		return this.f.getAbsolutePath();
	}

	public int getAnnee() {
		if (this.dateTrouve) {
			return Integer.parseInt(date);
		}
		return 0;
	}

	public String getTitre() {
		if (this.titre != null) {
			return this.titre.replaceAll("\\.", " ");
		}
		return "";
	}

	@Override
	protected void trouverParams() {
		nettoyerEspaces();
		trouverDate();
		trouverTitre();
		if(!dateEstValide()){
			this.date = "1896";
		}
	}
	
	private void trouverDate() {
		p = pDateC;
		// p = Pattern.compile("\\[[0-9]{4}\\]"); // [2001]
		m = p.matcher(this.origine);
		if (m.find()) {
			this.dateTrouve = true;
			this.date = this.origine.substring(m.start(), m.end());
			this.date = this.date.replaceAll("\\[", "");
			this.date = this.date.replaceAll("\\]", "");
			this.indexDate = m.start();
		} else {
			p = pDateD;
			// p = Pattern.compile("\\.[0-9]{4}\\."); // .2001.
			m = p.matcher(this.origine);
			if (m.find()) {
				this.dateTrouve = true;
				this.date = this.origine.substring((m.start() + 1), (m.end() - 1));
				this.indexDate = m.start();
			}
		}
	}

	private void trouverTitre() {
		if (dateTrouve) {
			this.titre = origine.substring(0, indexDate);
		} else if (estFic) {
			this.titre = origine.substring(0, origine.length() - ext.length());
		} else {
			this.titre = origine;
		}
		if (this.titre.endsWith(".")) {
			this.titre = titre.substring(0, titre.length() - 1);
		}
	}

	boolean dateEstValide() {
		boolean valide = false;
		if (dateTrouve && getAnnee() >= 1896) {
			valide = true;
		}
		return valide;
	}
}