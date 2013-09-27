package org.drfoliberg.films3000.data.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.drfoliberg.films3000.data.constants.Constantes;


public enum Departements {

	/**
	 * public static final String [] JOBS_WRITING =
	 * {"Screenplay","Story","Storyboard"
	 * ,"Author","Novel","Characters","Theatre Play"
	 * ,"Adaptation","Dialogue","Writer",}; public static final String []
	 * JOBS_DIRECTING = {"Director","Special Guest Director"}; public static
	 * final String [] JOBS_CAMERA = {"Director of Photography"}; public static
	 * final String [] JOBS_EDITING = {"Editor"}; public static final String []
	 * JOBS_PRODUCTION = {"Producer","Executive Producer"}; public static final
	 * String [] JOBS_SOUND = {"Original Music Composer"};
	 */

	WRITING(0, "writing", Constantes.JOBS_WRITING), DIRECTING(1, "directing", Constantes.JOBS_DIRECTING), ACTING(2,
			"actor", new String[0]), CAMERA(3, "camera", Constantes.JOBS_CAMERA), EDITING(4, "editing",
			Constantes.JOBS_EDITING), PRODUCTION(7, "production", Constantes.JOBS_PRODUCTION), SOUND(8, "sound",
			Constantes.JOBS_SOUND);

	public static final String[] DEPARTEMENTS = { "Writing", "Directing", "acting", "Camera", "Editing", "Production",
			"Sound" };

	public int getNoDep() {
		return noDep;
	}

	public String getNomDep() {
		return nomDep;
	}

	public ArrayList<String> getJobsConserver() {
		return jobsConserver;
	}

	int noDep;
	String nomDep;
	ArrayList<String> jobsConserver;

	Departements(int no, String nom, String[] possibles) {
		this.noDep = no;
		this.nomDep = nom;
		this.jobsConserver = new ArrayList<>();
		this.jobsConserver.addAll(Arrays.asList(possibles));
	}
}
