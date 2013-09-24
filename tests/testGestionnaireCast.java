package tests;

import java.io.File;
import java.util.ArrayList;

import org.drfoliberg.films3000.gestionDonnees.config.Departements;
import org.drfoliberg.films3000.gestionDonnees.config.GestionnaireCast;
import org.drfoliberg.films3000.modeles.films.personne.Role;


public class testGestionnaireCast {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testerGenererDefault();
		testerGetListeDefault();
	}

	public static void testerGenererDefault() {
		GestionnaireCast gc = new GestionnaireCast();
		ArrayList<Role> roles = new ArrayList<>();
		for (Departements d : Departements.values()) {
			Role r;
			for (String s : d.getJobsConserver()) {
				r = new Role(d.getNoDep(), s);
				roles.add(r);
				gc.ajouterRole(r);
			}
		}
		gc.enregisterListe(new File("default.ser"));
	}

	public static void testerGetListeDefault() {
		GestionnaireCast gc = new GestionnaireCast();
		gc.getDefault();
		System.out.println(gc.toString());
	}

}
