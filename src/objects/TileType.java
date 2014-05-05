package objects;

public enum TileType {
	EMPTY ("Empty"),
	RESIDENTIAL ("Residential"),
	COMMERCIAL ("Commercial"),
	INDUSTRIAL ("Industrial");
	
	String id;
	private TileType(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}