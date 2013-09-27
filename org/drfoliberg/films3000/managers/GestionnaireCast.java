package org.drfoliberg.films3000.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.drfoliberg.films3000.models.person.PersonMovieInfo;
import org.drfoliberg.films3000.models.person.Job;


public class GestionnaireCast implements IGestionnaireCast, Serializable {

	private static final long serialVersionUID = 4005209826661284907L;
	private ArrayList<Job> jobs;

	public GestionnaireCast() {
		this.jobs = new ArrayList<Job>();
	}

	@Override
	public boolean toKeep(PersonMovieInfo info) {
		Job role = new Job(info.getDepartement(), info.getJob());
		return toKeep(role);
	}

	@Override
	public boolean toKeep(Job job) {
		boolean contains = false;
		if (job != null && this.jobs.contains(job)) {
			contains = true;
		}
		return contains;
	}

	@Override
	public boolean readList(File f) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ArrayList<Job> roles = new ArrayList<>();
		boolean valid = true;
		try {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			if (o instanceof ArrayList) {
				ArrayList objects = (ArrayList) o;
				for (Object object : objects) {
					if (object instanceof Job) {
						roles.add((Job) object);
					}
				}
				if (roles.size() == 0) {
					valid = false;
				} else {
					this.jobs = roles;
				}
			} else {
				valid = false;
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

		return valid;
	}

	@Override
	public boolean saveList(File f) {
		boolean succes = true;
		try {
			FileOutputStream fos = new FileOutputStream(f.getAbsoluteFile());
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.jobs);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			succes = false;
		}
		return succes;
	}

	@Override
	public boolean addJob(Job role) {
		if (role != null && !this.jobs.contains(role)) {
			return this.jobs.add(role);
		}
		return false;
	}

	@Override
	public boolean removeJob(Job role) {
		if (role != null && this.jobs.contains(role)) {
			return this.jobs.remove(role);
		}
		return false;
	}

	@Override
	public void getDefault() {
		this.readList(new File("default.ser"));
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Voici la liste des rôles à conserver:\n");
		for (Job r : this.jobs) {
			sb.append(r.toString());
		}
		sb.append("fin de la liste");
		return sb.toString();
	}

}
