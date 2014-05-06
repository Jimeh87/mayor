package objects;

public enum TileType {
	EMPTY ("Empty", "E"),
	RESIDENTIAL ("Residential", "R"),
	COMMERCIAL ("Commercial", "C"),
	INDUSTRIAL ("Industrial", "I");
	
	String name;
	String buildingIdMainType;
	private TileType(String name, String buildingIdMainType) {
		this.name = name;
		this.buildingIdMainType = buildingIdMainType;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBuildingIdMainType() {
		return buildingIdMainType;
	}
	
	public boolean isZone() {
		return (this == TileType.RESIDENTIAL || this == TileType.COMMERCIAL || this == TileType.INDUSTRIAL);
	}
}