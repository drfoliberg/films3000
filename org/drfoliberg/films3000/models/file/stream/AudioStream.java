package org.drfoliberg.films3000.models.file.stream;

/**
 * 
 * @author justin
 * 
 */
public class AudioStream extends Stream {

	public String codec;
	public int bitrate;
	public String channels;
	public String language;
	public boolean commentary;
	public int lengthMs;

	/**
	 * 
	 * @param codec
	 * @param bitrate
	 * @param channels
	 * @param language
	 * @param commentary
	 * @param lengthMs
	 * @param idStream
	 */
	public AudioStream(String codec, int bitrate, String channels, String language, boolean commentary, int lengthMs,
			int idStream) {
		super(idStream);
		this.codec = codec;
		this.bitrate = bitrate;
		this.channels = channels;
		this.language = language;
		this.commentary = commentary;
		this.lengthMs = lengthMs;
	}

	public String getCodec() {
		return codec;
	}

	public void setCodec(String codec) {
		this.codec = codec;
	}

	public int getBitrate() {
		return bitrate;
	}

	public void setBitrate(int bitrate) {
		this.bitrate = bitrate;
	}

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public String getLangue() {
		return language;
	}

	public void setLangue(String langue) {
		this.language = langue;
	}

	public boolean isCommentaire() {
		return commentary;
	}

	public void setCommentary(boolean commentary) {
		this.commentary = commentary;
	}

	public int getLengthMs() {
		return lengthMs;
	}

	public void setLengthMs(int lengthMs) {
		this.lengthMs = lengthMs;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("StreamAudio id:");
		sb.append(this.streamId);
		sb.append("\n");
		sb.append(this.lengthMs / (1000 * 60));
		sb.append("min ");
		sb.append(this.codec);
		sb.append(" ");
		sb.append(this.channels);
		sb.append("ch ");
		sb.append(this.bitrate);
		sb.append(" kbps");
		sb.append("\n");
		sb.append(this.language);

		if (this.commentary) {
			sb.append(" Commentary");
		}
		sb.append("\n");
		return sb.toString();
	}
}