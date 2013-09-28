package org.drfoliberg.films3000.managers;

import java.io.File;
import java.util.ArrayList;

import org.drfoliberg.films3000.models.file.MovieFile;
import org.drfoliberg.films3000.models.file.Snapshot;

public class MovieFileManager {

	private Snapshot snapshot;
	private ArrayList<MovieFile> currentFiles;
	private ArrayList<MovieFile> newFiles;
	private ArrayList<MovieFile> deletedFiles;

	public ArrayList<MovieFile> findRenamedFiles() {
		ArrayList<MovieFile> renamed = new ArrayList<>();
		for (MovieFile mf : this.deletedFiles) {
			if (newFiles.contains(mf)) {
				renamed.add(mf);
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
