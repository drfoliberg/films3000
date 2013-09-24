package org.drfoliberg.films3000.gestionDonnees.donnees.baseDonnees.structure.types;

public class Type {

	private String type;
	private int precision;

	public Type(String type, int precision) {
		this.precision = precision;
		this.type = type;
	}

	public Type(String type) {
		this.type = type;
		this.precision = -1;
	}

	public String getType() {
		return type;
	}

	public int getPrecision() {
		return precision;
	}
	
	@Override
	public String toString(){
		if(precision != -1){
			return this.type+"("+this.precision+")";
		}else{
			return this.type;
		}
	}
}
