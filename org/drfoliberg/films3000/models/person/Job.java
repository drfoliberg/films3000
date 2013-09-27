package org.drfoliberg.films3000.models.person;

import java.io.Serializable;

public class Job implements Serializable {

	private static final long serialVersionUID = 1388183333182608403L;
	private int departement;
	private String job;

	public Job(int departement, String role) {
		this.departement = departement;
		this.job = role;
	}

	public int getDepartement() {
		return departement;
	}

	public void setDepartement(int departement) {
		this.departement = departement;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public boolean equals(Object objet) {
		if (objet != null && objet instanceof Job) {
			if (this.job.equals(((Job) objet).getJob()) && this.departement == (((Job) objet).getDepartement())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getDepartement());
		sb.append(" ");
		sb.append(this.getJob());
		sb.append("\n");
		return sb.toString();
	}
}
