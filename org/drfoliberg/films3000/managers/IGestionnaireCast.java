package org.drfoliberg.films3000.managers;

import java.io.File;

import org.drfoliberg.films3000.models.person.PersonMovieInfo;
import org.drfoliberg.films3000.models.person.Job;


public interface IGestionnaireCast {

	public boolean toKeep(PersonMovieInfo info);
	
	public boolean toKeep(Job role);

	public boolean readList(File fichier);

	public boolean saveList(File fichier);
	
	public boolean addJob(Job role);

	public boolean removeJob(Job role);

	public void getDefault();

}
