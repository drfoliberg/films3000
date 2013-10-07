package org.drfoliberg.films3000.managers;

import org.drfoliberg.films3000.models.file.BaseFile;
import org.drfoliberg.films3000.models.file.Snapshot;

public class SnapshotManager {

	private Snapshot old;
	private Snapshot current;

	public SnapshotManager() {
		this.old = getLastSnapshot();
		this.current = new Snapshot();
	}

	/**
	 * Resets all snapshots in memory to null.
	 */
	public void clear() {
		this.old = null;
		this.current = null;
	}

	/**
	 * Fetchs from database the last snapshot according to it's timestamp.
	 * 
	 * @return The last snapshot
	 */
	public Snapshot getLastSnapshot() {
		if (this.old == null) {
			this.old = new Snapshot();
		}
		// TODO read from database the snapshot with the lastest unix timestamp
		return this.old;
	}

	/**
	 * Fetchs current snapshot from the files present on disk. Will stay cached
	 * untill SnapshotManager.clear() is called.
	 * 
	 * @return The "current" snapshot
	 */
	public Snapshot getCurrentSnapshot(String[] roots) {
		if (this.current == null) {
			this.current.getCurrentSnapshot(roots);
		}

		return this.current;
	}

	/**
	 * Commits the snapshot to the database.
	 * 
	 * @return True if success.
	 */
	public boolean commit(Snapshot s) {
		s.setTimestamp(System.currentTimeMillis());
		for (BaseFile f : s.getFiles()) {
			f.getFileSum();
		}
		// TODO commit to database
		return true;
	}
}
