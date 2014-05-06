package objects;

public enum TileType {
	EMPTY ("Empty", "E"),
	RESIDENTIAL ("Residential", "R"),
	COMMERCIAL ("Commercial", "C"),
	INDUSTRIAL ("Industrial", "I");
	
	String name;
	String buildingIdPrefix;
	private TileType(String name, String buildingIdPrefix) {
		this.name = name;
		this.buildingIdPrefix = buildingIdPrefix;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBuildingPrefix() {
		return buildingIdPrefix;
		
	}
}