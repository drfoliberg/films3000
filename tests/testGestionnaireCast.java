package tests;

import java.io.File;
import java.util.ArrayList;

import org.drfoliberg.films3000.data.config.Departements;
import org.drfoliberg.films3000.managers.GestionnaireCast;
import org.drfoliberg.films3000.models.person.Job;


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
		ArrayList<Job> roles = new ArrayList<>();
		for (Departements d : Departements.values()) {
			Job r;
			for (String s : d.getJobsConserver()) {
				r = new Job(d.getNoDep(), s);
				roles.add(r);
				gc.addJob(r);
			}
		}
		gc.saveList(new File("default.ser"));
	}

	public static void testerGetListeDefault() {
		GestionnaireCast gc = new GestionnaireCast();
		gc.getDefault();
		System.out.println(gc.toString());
	}

}
