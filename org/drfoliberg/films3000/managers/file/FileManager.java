package org.drfoliberg.films3000.managers.file;

import java.io.File;
import java.util.ArrayList;

import org.drfoliberg.films3000.models.file.BaseFile;
import org.drfoliberg.films3000.models.file.MovieFile;
import org.drfoliberg.films3000.models.file.Snapshot;

public class FileManager {

	private Snapshot snapshot;
	private ArrayList<BaseFile> currentFiles;
	private ArrayList<BaseFile> newFiles;
	private ArrayList<BaseFile> deletedFiles;

	/**
	 * Finds new files, renamed files and deleted files
	 * 
	 * @param roots
	 *            The root directories to look in
	 */
	public void update(String[] roots) {
		if (this.snapshot != null) {
			getCurrentListing(roots);
			for (BaseFile f : currentFiles) {
				if (!snapshot.containsNameLength(f)) {
					if (snapshot.containsSum(f)) {
						//TODO
					} else {
						this.newFiles.add(f);
					}
				}
			}
		}
	}

	/**
	 * Method that updates this.currentFiles
	 * 
	 * @param roots
	 *            The root directories to look in
	 */
	public void getCurrentListing(String[] roots) {
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
					this.currentFiles.add(new MovieFile(file));
				}
			}
		}
	}

	public ArrayList<BaseFile> findRenamedFiles() {
		ArrayList<BaseFile> renamed = new ArrayList<>();
		for (BaseFile mf : this.deletedFiles) {
			if (newFiles.contains(mf)) {
				renamed.add(mf);
				newFiles.remove(mf);
				deletedFiles.remove(mf);
			}
		}
		return renamed;
	}

	public ArrayList<MovieFile> getFiles() {
		return null;
	}

	public void removeFile(int idFichier) {

	}

	public ArrayList<MovieFile> getMovieFile(File fichier) {
		return null;
	}

	public void removeMovieFile(MovieFile fichier) {

	}

	public void addMovieFile(MovieFile fichier) {

	}

}
