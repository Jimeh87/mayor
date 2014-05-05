package objects;

public enum TileType {
	EMPTY ("Hand"),
	RESIDENTIAL ("Residential"),
	COMMERCIAL ("Commercial"),
	INDUSTRIAL ("Industrial"),
	BULLDOZE ("Empty");
	
	String id;
	private TileType(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}