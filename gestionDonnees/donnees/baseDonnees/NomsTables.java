package gestionDonnees.donnees.baseDonnees;

public enum NomsTables {
	REALISATEUR("realisateurs", "films"), ACTEUR("acteurs", "films"), SERIE("series", "films"), PAYS("pays", "films"), GENRE("genres", "films"), LANGUE("langues"),
	STREAMAUDIO("stream_audio","fichiers"),STREAMVIDEO("stream_video","fichiers"), FICHIER("fichiers","films");
	private String tblJointure;
	private String tbl;
	public String champJointure;

	private NomsTables(String tbl, String tblJointure) {
		this.tbl = tbl;
		this.tblJointure = tbl + "_has_" + tblJointure;
		champJointure = tbl + "_id";
	}
	
	private NomsTables(String tbl){
		this(tbl, tbl + "_has_films");
	}

	public String tbl() {
		return tbl;
	}

	public String tblJointure() {
		return tblJointure;
	}
}
