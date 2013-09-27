package org.drfoliberg.films3000.models.file.stream;

/**
 * 
 * @author justin
 */
public class VideoStream extends Stream {

	public String codec;
	public int bitrate;
	public String quality;
	public int height;
	public int weight;
	public double fps;
	public int lengthMs;

	/**
	 * 
	 * @param codec
	 * @param bitrate
	 * @param quality
	 * @param height
	 * @param weight
	 * @param fps
	 * @param lengthMs
	 * @param idStream
	 * @param idFichier
	 */
	public VideoStream(String codec, int bitrate, String quality, int height, int weight, double fps, int lengthMs,
			int idStream) {
		super(idStream);
		this.codec = codec;
		this.bitrate = bitrate;
		this.quality = quality;
		this.height = height;
		this.weight = weight;
		this.fps = fps;
		this.lengthMs = lengthMs;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("StreamAudio id:");
		sb.append(this.streamId);
		sb.append("\n");
		sb.append(this.lengthMs/(1000*60));
		sb.append("min ");
		sb.append(this.codec);
		sb.append(" ");
		sb.append(this.fps);
		sb.append("fps ");
		sb.append(this.bitrate);
		sb.append(" kbps ");
		sb.append(this.quality);

		sb.append("\n");
		return sb.toString();
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

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public double getFps() {
		return fps;
	}

	public void setFps(double fps) {
		this.fps = fps;
	}

	public int getLengthMs() {
		return lengthMs;
	}

	public void setLengthMs(int lengthMs) {
		this.lengthMs = lengthMs;
	}

}
