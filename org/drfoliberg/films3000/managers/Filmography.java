package org.drfoliberg.films3000.managers;

import java.util.ArrayList;

import org.drfoliberg.films3000.models.person.PersonMovieInfo;

public class Filmography {

	private ArrayList<PersonMovieInfo> jobs;
	private int personId;

	public Filmography(int personId) {
		jobs = new ArrayList<>();
		this.personId = personId;
	}
	
	public PersonMovieInfo getMovie(int idTmdb){
		PersonMovieInfo job = null;

		for (PersonMovieInfo movieInfo : jobs) {
			if(movieInfo.getIdFilm() == idTmdb){
				job = movieInfo;
				break;
			}
		}
		
		return job;
	}
	
	public int getNbJobs(){
		return jobs.size();
	}

	public void addJob(PersonMovieInfo film) {
		if (film != null) {
			this.jobs.add(film);
		}
	}
	
	public void removeJob(int movieId){
		//TODO remove a job from the list
	}

	public int getPersonId() {
		return this.personId;
	}

	public ArrayList<PersonMovieInfo> getJobs() {
		return this.jobs;
	}
}
