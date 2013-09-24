package org.drfoliberg.films3000.modeles.fichiers;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Renommeur {

	protected File f;
	protected String origine;
	protected boolean estFic;
	protected String ext;
	protected String nomFinal;
	protected Pattern p;
	protected Matcher m;
	protected boolean changement;
	
	public Renommeur(File f) {
		this.f = f;
		this.origine = f.getName();
		this.nomFinal = this.origine;
		this.estFic = f.isFile();
		if (estFic) {
			this.ext = trouverExt();
		}
		trouverParams();
	}

	protected void nettoyerEspaces() {
		this.origine = this.origine.replace(" ", ".");
		this.origine = this.origine.replace("(", "[");
		this.origine = this.origine.replace(")", "]");
	}
	
	private String trouverExt() {
		return this.origine.substring(this.origine.lastIndexOf('.'));
	}
	
	protected abstract void trouverParams();

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public boolean estFic() {
		return estFic;
	}

	public String getNomFinal() {
		return nomFinal;
	}

	public boolean isChangement() {
		return changement;
	}
	
	
	
}
