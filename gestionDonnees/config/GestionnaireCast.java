package gestionDonnees.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import modeles.films.personne.InfoFilm;
import modeles.films.personne.Role;

public class GestionnaireCast implements IGestionnaireCast, Serializable {

	private static final long serialVersionUID = 4005209826661284907L;
	private ArrayList<Role> roles;

	public GestionnaireCast() {
		this.roles = new ArrayList<Role>();
	}

	@Override
	public boolean garder(InfoFilm info) {
		Role role = new Role(info.getDepartement(), info.getJob());
		return garder(role);
	}

	@Override
	public boolean garder(Role role) {
		boolean contient = false;
		if (role != null && this.roles.contains(role)) {
			contient = true;
		}
		return contient;
	}

	@Override
	public boolean ouvrirListe(File fichier) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ArrayList<Role> roles = new ArrayList<>();
		boolean valide = true;
		try {
			fis = new FileInputStream(fichier);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			if (o instanceof ArrayList) {
				ArrayList listeObjects = (ArrayList) o;
				for (Object object : listeObjects) {
					if (object instanceof Role) {
						roles.add((Role) object);
					}
				}
				if (roles.size() == 0) {
					valide = false;
				} else {
					this.roles = roles;
				}
			} else {
				valide = false;
			}
			if (ois != null) {
				ois.close();
			}
			if (fis != null) {
				fis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return valide;
	}

	@Override
	public boolean enregisterListe(File fichier) {
		boolean succes = true;
		try {
			FileOutputStream fos = new FileOutputStream(fichier.getAbsoluteFile());
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.roles);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			succes = false;
		}
		return succes;
	}

	@Override
	public boolean ajouterRole(Role role) {
		if (role != null && !this.roles.contains(role)) {
			return this.roles.add(role);
		}
		return false;
	}

	@Override
	public boolean retirer(Role role) {
		if (role != null && this.roles.contains(role)) {
			return this.roles.remove(role);
		}
		return false;
	}

	@Override
	public void getDefault() {
		this.ouvrirListe(new File("default.ser"));
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Voici la liste des rôles à conserver:\n");
		for (Role r : this.roles) {
			sb.append(r.toString());
		}
		sb.append("fin de la liste");
		return sb.toString();
	}

}
