package org.drfoliberg.films3000.models.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Snapshot {

	private ArrayList<BaseFile> oldFiles;
	private long unixTime;

	public Snapshot() {
		this.oldFiles = new ArrayList<>();
		this.unixTime = System.currentTimeMillis();
	}

	public ArrayList<BaseFile> getFiles() {
		return this.oldFiles;
	}

	public boolean commit() {
		this.unixTime = System.currentTimeMillis();
		for (BaseFile f : oldFiles) {
			f.getFileSum();
		}
		return true;
	}

	public BaseFile getFile(long sum) {
		for (BaseFile bf : oldFiles) {
			if (bf.getFileSum() == sum) {
				return bf;
			}
		}
		return null;
	}

	public boolean containsNameLength(BaseFile f) {
		boolean contains = false;

		for (BaseFile file : this.oldFiles) {
			if (file.equalsLength(f)) {
				contains = true;
				break;
			}
		}

		return contains;
	}

	public boolean containsSum(BaseFile f) {

		for (BaseFile bf : oldFiles) {
			if (bf.equalsSum(f)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Method that updates the snapshot with the content of the roots
	 * 
	 * @param roots
	 *            The root directories to look in
	 */
	public void getCurrentSnapshot(String[] roots) {
		for (String r : roots) {
			File f = new File(r);
			if (f.isDirectory() && f.canRead()) {
				explore(f);
			}
		}
	}

	/**
	 * Recursive method to find files in a directory
	 * 
	 * @param f
	 *            The directory to look in
	 */
	public void explore(File f) {
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					explore(file);
				} else {
					this.oldFiles.add(new MovieFile(file));
				}
			}
		}
	}

}
