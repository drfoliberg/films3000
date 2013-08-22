package modeles.films.personne;

import java.io.Serializable;

public class Role implements Serializable{

	private static final long serialVersionUID = 1388183333182608403L;
	private int departement;
	private String role;

	public Role(int departement, String role) {
		this.departement = departement;
		this.role = role;
	}

	public int getDepartement() {
		return departement;
	}

	public void setDepartement(int departement) {
		this.departement = departement;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object objet) {
		if (objet != null && objet instanceof Role) {
			if (this.role.equals(((Role) objet).getRole()) && this.departement == (((Role) objet).getDepartement())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.getDepartement());
		sb.append(" ");
		sb.append(this.getRole());
		sb.append("\n");
		return sb.toString();
	}
}
