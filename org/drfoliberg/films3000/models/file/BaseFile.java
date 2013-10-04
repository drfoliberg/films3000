package org.drfoliberg.films3000.models.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.zip.CRC32;

import org.drfoliberg.films3000.models.file.stream.Stream;

/**
 * The base file containing base information used to compare files and media
 * information.
 * 
 * @author Justin Duplessis
 * 
 */
public class BaseFile {

	private final static int pieceSize = 32 * 1024;
	private final static long skipSize = 256 * 1024 * 1024 - pieceSize;

	private int apiID;
	private int lengthSeconds;
	private String format;
	private ArrayList<Stream> streams;
	private String path;
	private String fileName;

	private long fileSum;
	private long length;
	private long[] pieceSums;
	private long pieces;

	private RandomAccessFile raf;
	private File file;

	/**
	 * Base constructor for a new BaseFile from an existing file on the system.
	 * 
	 * @param f
	 *            The file on disk.
	 */
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

	/**
	 * Returns the checksum of a single piece of the file. Will only access to
	 * disk and calculate checksum if the checksum array (pieceSums) contains 0
	 * at the piece position.
	 * 
	 * @param pieceNum
	 *            The piece's position in the file.
	 * @return A long representing the crc32 sum of the piece TODO Implement a
	 *         better caching algorithm
	 * @throws IOException
	 *             If accessing the file fails
	 */
	public long getPieceSum(int pieceNum) throws IOException {

		if (pieceNum < 0 || pieceNum >= pieceSums.length) {
			return 0;
		}

		if (this.pieceSums.length < pieceNum || this.pieceSums[pieceNum] == 0) {
			CRC32 crc = new CRC32();
			crc.update(this.getPiece(pieceNum));
			this.pieceSums[pieceNum] = crc.getValue();
		}

		return this.pieceSums[pieceNum];
	}

	/**
	 * Returns a byte array containing the byte values of the piece. Will always
	 * try to read the file and get new values when called.
	 * 
	 * @param pieceNum
	 *            The piece position
	 * @return The byte array of the size of a piece size
	 * @throws IOException
	 *             If accessing the file fails
	 */
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

	/**
	 * 
	 * @return A long representing the crc32 sum of the pieces of the file.
	 * @throws IOException
	 *             If accessing the file fails.
	 */
	public long getFileSum() {

		try {
			CRC32 crc = new CRC32();
			int i = 0;
			while (i++ != this.pieces) {
				crc.update(this.getPiece(i));
			}
			if (this.raf != null) {
				this.raf.close();
			}
			this.fileSum = crc.getValue();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.fileSum;
	}

	/**
	 * Checks if file equals another with only the filename and length for
	 * minimal disk access.
	 * 
	 * @param o
	 *            BaseFile to compare
	 * @return if files have same name and length
	 */
	public boolean equalsLength(Object o) {
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

	/**
	 * Checks if the BaseFile equals another with only the sum. As pieces are
	 * compared, it will stop right when a piece does not match.
	 * 
	 * @param bf
	 *            The basefile to compare to the current objet
	 * @return Returns true if all piecesums are the same
	 */
	public boolean equalsSum(BaseFile bf) {
		if (bf != null) {
			boolean equal = true;
			int i = 0;
			try {
				while (equal && i != this.pieces) {
					if (this.getPieceSum(i) != bf.getPieceSum(i)) {
						equal = false;
					}
					i++;
				}
			} catch (IOException e) {
				return false;
			}
		}
		return false;

	}

	@Override
	/**
	 * Checks if the two files have the same checksum, length and path.
	 * 
	 */
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof BaseFile)) {
			return false;
		}
		BaseFile f = (BaseFile) o;

		if (f.getLength() == this.length && f.getFileName().equals(this.getFileName())
				&& f.getPath().equals(this.getPath())) {
			return f.equalsSum(f);
		} else {
			return false;
		}

	}

	public void setFileSum(int fileSum) {
		this.fileSum = fileSum;
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
