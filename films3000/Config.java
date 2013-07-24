package films3000;

public class Config {
	private String clefApi;
	private String langue;
	
	public Config(String clef, String langue){
		this.clefApi = clef;
		this.langue = langue;
	}
	
	public String getLangue(){
		return this.langue;
	}
	
	public String getClefApi(){
		return this.clefApi;
	}
}
