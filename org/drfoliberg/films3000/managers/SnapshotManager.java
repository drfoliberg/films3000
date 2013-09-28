package org.drfoliberg.films3000.managers;

import org.drfoliberg.films3000.models.file.Snapshot;

public abstract class SnapshotManager {

	public SnapshotManager() {

	}

	public abstract boolean updateSnapshot(Snapshot newSnapshot);

	public abstract Snapshot getLastSnapshot();
}
