package org.drfoliberg.films3000.managers.file;

import java.util.ArrayList;

import org.drfoliberg.films3000.managers.SnapshotManager;
import org.drfoliberg.films3000.models.file.BaseFile;
import org.drfoliberg.films3000.models.file.Snapshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileManager {

	// TODO Implement better file tree analysis to check for the last modified
	// date. Will be compared to the snapshot's unix timestamp.

	private SnapshotManager snapshotManager;
	private static final Logger LOG = LoggerFactory.getLogger(FileManager.class);

	private ArrayList<BaseFile> lostFiles = new ArrayList<>();
	private ArrayList<BaseFile> newFiles = new ArrayList<>();
	private ArrayList<BaseFile> renamedFiles = new ArrayList<>();
	private ArrayList<BaseFile> changedFiles = new ArrayList<>();

	public FileManager() {
		this.snapshotManager = new SnapshotManager();
		this.snapshotManager.getLastSnapshot();
	}

	/**
	 * Clears all data from last analysis.
	 */
	public void clear() {
		this.lostFiles = new ArrayList<>();
		this.newFiles = new ArrayList<>();
		this.renamedFiles = new ArrayList<>();
		this.changedFiles = new ArrayList<>();
	}

	/**
	 * Finds new files, renamed files and deleted files
	 * 
	 * @param roots
	 *            The root directories to look in
	 * @return True if update completed without errors.
	 */
	public boolean update(String[] roots) {

		this.clear();
		if (snapshotManager.getLastSnapshot() == null) {
			return false;
		}

		Snapshot newSnapshot = new Snapshot();
		Snapshot lastSnapshot = snapshotManager.getLastSnapshot();

		newSnapshot.getCurrentSnapshot(roots);
		lostFiles = lastSnapshot.getFiles();

		// Get all new files with a path and length check
		for (BaseFile f : lastSnapshot.getFiles()) {
			if (!newSnapshot.containsNameLength(f)) {
				lostFiles.add(f);
			}
		}
		// Get all lost files with a path and length check
		for (BaseFile f : newSnapshot.getFiles()) {
			if (!lastSnapshot.containsNameLength(f)) {
				newFiles.add(f);
			}
		}

		// Get renamed files with a hash check and changed files
		for (BaseFile oldFile : lostFiles) {
			if (newSnapshot.containsSum(oldFile)) {
				BaseFile newFile = newSnapshot.getFile(oldFile.getFileSum());
				renamedFiles.add(newFile);
				lostFiles.remove(oldFile);
				LOG.info("File " + oldFile.getFileName() + " was renamed as " + newFile.getFileName()
						+ ". Found with the common hash " + newFile.getFileSum() + ".");
			} else if (newSnapshot.containsPathName(oldFile)) {
				BaseFile match = newSnapshot.getFile(oldFile.getPath(), oldFile.getFileName());
				changedFiles.add(match);
			}
		}
		snapshotManager.commit(newSnapshot);

		return true;
	}

}
