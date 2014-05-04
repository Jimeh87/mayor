package objects;

public enum TileType {
	EMPTY ("empty"),
	RESIDENTIAL ("residential"),
	COMMERCIAL ("commercial"),
	INDUSTRIAL ("industrial");
	
	String id;
	private TileType(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}