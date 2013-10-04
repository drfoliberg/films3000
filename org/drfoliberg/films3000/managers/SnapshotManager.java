package org.drfoliberg.films3000.managers;

import org.drfoliberg.films3000.models.file.Snapshot;

public abstract class SnapshotManager {

	private Snapshot old;
	private Snapshot current;

	public boolean updateCurrentSnapshot(String[] roots) {
		this.current = new Snapshot();
		this.current.getCurrentSnapshot(roots);
		return false;
	}

	public Snapshot getLastSnapshot() {
		if (this.old == null) {
			this.old = new Snapshot();
		}
		return this.old;
	}

	public Snapshot getCurrentSnapshot() {

		return this.current;
	}

	public boolean commit(Snapshot s) {
		return false;
	}
}
