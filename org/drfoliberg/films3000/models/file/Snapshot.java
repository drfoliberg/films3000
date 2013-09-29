package org.drfoliberg.films3000.models.file;

import java.util.ArrayList;
import java.util.Date;

public class Snapshot {

	private ArrayList<BaseFile> oldFiles;
	private Date date;

	public Snapshot() {

	}

	public ArrayList<BaseFile> getOldFiles() {
		return this.oldFiles;
	}

	public boolean containsNameLength(BaseFile f) {
		boolean contains = false;
		
		for (BaseFile file : this.oldFiles) {
			if (file.equalsByLength(f)) {
				contains = true;
				break;
			}
		}
		
		return contains;
	}
	
	public boolean containsSum(BaseFile f){
		boolean contains = false;
		
		for (BaseFile file : this.oldFiles) {
			if (file.equals(f)) {
				contains = true;
				break;
			}
		}
		
		return contains;
	}

}
