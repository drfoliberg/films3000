package org.drfoliberg.films3000.models.file;

import java.io.File;
import java.util.ArrayList;

public class Snapshot {

	private ArrayList<BaseFile> oldFiles;
	private long timestamp;

	public Snapshot() {
		this.oldFiles = new ArrayList<>();
	}

	public ArrayList<BaseFile> getFiles() {
		return this.oldFiles;
	}

	/**
	 * Look for a BaseFile with only the same long representation.
	 * 
	 * @param sum
	 *            The long representation to look for
	 * @return The basefile found with the same long representation or null if
	 *         no match
	 */
	public BaseFile getFile(long sum) {
		for (BaseFile bf : oldFiles) {
			if (bf.getFileSum() == sum) {
				return bf;
			}
		}
		return null;
	}

	/**
	 * Look for a BaseFile with only the filename and path given as case
	 * sensitive Strings.
	 * 
	 * @param path
	 *            The (absolute) pathname
	 * @param fileName
	 *            The filename in the path
	 * @return The basefile found or null if not match
	 */
	public BaseFile getFile(String path, String fileName) {
		for (BaseFile bf : oldFiles) {
			if (bf.getFileName().equals(fileName) && bf.getPath().equals(path)) {
				return bf;
			}
		}
		return null;
	}

	public boolean containsPathName(BaseFile f) {
		boolean contains = false;

		for (BaseFile file : this.oldFiles) {
			if (file.equalsNamePath(f)) {
				contains = true;
				break;
			}
		}

		return contains;
	}

	public boolean containsNameLength(BaseFile f) {
		boolean contains = false;

		for (BaseFile file : this.oldFiles) {
			if (file.equalsNameLength(f)) {
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

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
