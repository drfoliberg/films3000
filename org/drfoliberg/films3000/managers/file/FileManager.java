package org.drfoliberg.films3000.managers.file;

import java.util.ArrayList;

import org.drfoliberg.films3000.managers.SnapshotManager;
import org.drfoliberg.films3000.models.file.BaseFile;
import org.drfoliberg.films3000.models.file.Snapshot;

public class FileManager {

	// TODO Change how snapshots are handle. All snapshots will come from the
	// snapshotManager
	private Snapshot snapshot;
	private SnapshotManager snapshotManager;

	/**
	 * Finds new files, renamed files and deleted files
	 * 
	 * @param roots
	 *            The root directories to look in
	 */
	public boolean update(String[] roots) {
		if (snapshot == null) {
			return false;
		}

		Snapshot newSnapshot = new Snapshot();
		newSnapshot.getCurrentSnapshot(roots);

		ArrayList<BaseFile> lostFiles = snapshot.getFiles();
		ArrayList<BaseFile> newFiles = new ArrayList<>();
		ArrayList<BaseFile> renamedFiles = new ArrayList<>();
		ArrayList<BaseFile> changedFiles = new ArrayList<>();
		ArrayList<BaseFile> unchangedFiles = new ArrayList<>();

		for (BaseFile f : newSnapshot.getFiles()) {

			if (!snapshot.containsNameLength(f)) {
				// new , modified or renamed file
				if (snapshot.containsSum(f)) {
					// modified or renamed file
					BaseFile old = snapshot.getFile(f.getFileSum());
					if (old.getFileName().equals(f.getFileName()) && old.getPath().equals(f.getPath())) {
						// modified content
						// TODO have mediainfo check back the streams of the
						// file
						changedFiles.add(f);
					} else {
						// renamed file
						// TODO change the file in the base with the sum as the
						// key
						renamedFiles.add(f);
					}
				} else {
					// this is a new file
					// TODO scan this file for movie
					newFiles.add(f);
				}
			} else {
				// file is unchanged
				unchangedFiles.add(f);
			}
		}

		for (BaseFile f : snapshot.getFiles()) {
			if (!newSnapshot.containsSum(f)) {
				lostFiles.add(f);
				// TODO notify of a lost file
				// TODO better "lost file detection"
			}
		}
		return true;
	}

}
