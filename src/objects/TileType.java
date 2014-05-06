package objects;

/**
 * Used to indicate the TileType. Also contains the TileMainType which is used to determine image used for tile.
 * @author Jim
 */
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
	
	/**
	 * Returns the name of the tile.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the name of the building id main type.
	 * @return
	 */
	public String getBuildingIdMainType() {
		return buildingIdMainType;
	}
	
	/**
	 * returns if the building TileType is a zone or not.
	 * @return
	 */
	public boolean isZone() {
		return (this == TileType.RESIDENTIAL || this == TileType.COMMERCIAL || this == TileType.INDUSTRIAL);
	}
}