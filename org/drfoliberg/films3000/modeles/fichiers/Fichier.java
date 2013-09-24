package org.drfoliberg.films3000.modeles.fichiers;

import java.io.File;
import java.util.ArrayList;

import org.drfoliberg.films3000.modeles.fichiers.stream.Stream;

public class Fichier {

	private int fileHash;
	private int apiID;
	private int lengthSeconds;
	private long length;

	private String format;
	private ArrayList<Stream> streams;
	private String path;
	private String fileName;

	public Fichier(File f) {
		this.length = f.length();
		this.path = f.getPath();
		this.fileName = f.getName();
		this.format = "Unknown";
		this.streams = new ArrayList<>();
	}

	private static long getHash(File f) {
		//TODO implement hashing
		return 0;
	}

	public int getFileHash() {
		return fileHash;
	}

	public void setFileHash(int fileHash) {
		this.fileHash = fileHash;
	}

	public int getApiID() {
		return apiID;
	}

	public void setApiID(int apiID) {
		this.apiID = apiID;
	}

	public int getLengthSeconds() {
		return lengthSeconds;
	}

	public void setLengthSeconds(int lengthSeconds) {
		this.lengthSeconds = lengthSeconds;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public ArrayList<Stream> getStreams() {
		return streams;
	}

	public void setStreams(ArrayList<Stream> streams) {
		this.streams = streams;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
