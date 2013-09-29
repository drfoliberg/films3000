package org.drfoliberg.films3000.models.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.zip.CRC32;

import org.drfoliberg.films3000.models.file.stream.Stream;

public class BaseFile {

	private final static int pieceSize = 32 * 1024;
	private final static long skipSize = 256 * 1024 * 1024 - pieceSize;

	private int apiID;
	private int lengthSeconds;
	private String format;
	private ArrayList<Stream> streams;
	private String path;
	private String fileName;

	private int fileHash;
	private long length;
	private long[] pieceSums;
	private long pieces;

	private RandomAccessFile raf;
	private File file;

	public BaseFile(File f) {
		this.file = f;
		this.length = f.length();
		this.path = f.getPath();
		this.fileName = f.getName();
		this.format = "Unknown";
		this.streams = new ArrayList<>();
		this.pieces = this.file.length() / (pieceSize + skipSize);
		this.pieceSums = new long[(int) this.pieces];
	}

	public long getPieceSum(int pieceNum) throws IOException {
		//TODO add cached values
		if (this.pieceSums.length < pieceNum || this.pieceSums[pieceNum] == 0) {
			CRC32 crc = new CRC32();
			crc.update(this.getPiece(pieceNum));
			this.pieceSums[pieceNum] = crc.getValue();
		}

		return this.pieceSums[pieceNum];
	}

	private byte[] getPiece(int pieceNum) throws IOException {

		if (this.raf == null) {
			raf = new RandomAccessFile(this.file, "r");
		}
		long pos = (skipSize + pieceSize) * pieceNum;
		if (pos < 0) {
			return new byte[0];
		}
		if (pos > this.length) {
			pos = this.length - pieceSize;
		}

		this.raf.seek(pos);
		byte[] piece = new byte[pieceSize];
		raf.read(piece);
		return piece;

	}

	public long getCheckSum() throws IOException {

		CRC32 crc = new CRC32();
		int i = 0;
		while (i++ != this.pieces) {
			crc.update(this.getPiece(i));
		}
		this.raf.close();
		return crc.getValue();
	}

	public boolean equalsByLength(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof BaseFile)) {
			return false;
		}
		boolean equal = false;
		BaseFile f = (BaseFile) o;

		if (f.getLength() == this.length && f.getFileName().equals(this.getFileName())) {
			equal = true;
		}

		return equal;

	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof BaseFile)) {
			return false;
		}
		boolean equal = true;
		BaseFile f = (BaseFile) o;

		if (f.getLength() == this.length) {
			int i = 0;
			try {
				while (equal && i != this.pieces) {
					if (this.getPieceSum(i) != f.getPieceSum(i)) {
						equal = false;
					}
					i++;
				}
			} catch (IOException e) {
				return false;
			}
		} else {
			return false;
		}

		return equal;

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

	public long[] getPieceSums() {
		return pieceSums;
	}

	public void setPieceSums(long[] pieceSums) {
		this.pieceSums = pieceSums;
	}

	public long getPieces() {
		return pieces;
	}

	public void setPieces(long pieces) {
		this.pieces = pieces;
	}

	public File getFile() {
		return file;
	}

}
