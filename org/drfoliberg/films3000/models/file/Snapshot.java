package org.drfoliberg.films3000.models.file;

import java.util.ArrayList;
import java.util.Date;

public class Snapshot {
	
	private ArrayList<MovieFile> oldFiles;
	private Date date;
	
	public Snapshot() {
		
	}
	
	
	public ArrayList<MovieFile> getOldFiles(){
		return this.oldFiles;
	}
	
}
