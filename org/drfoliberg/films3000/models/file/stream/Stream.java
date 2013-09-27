package org.drfoliberg.films3000.models.file.stream;

/**
 * 
 * @author justin
 */
public abstract class Stream {

	protected int streamId;
	protected int fileId;

	public Stream(int streamId) {
		this.streamId = streamId;
	}

	public int getStreamId() {
		return streamId;
	}

	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	
}
