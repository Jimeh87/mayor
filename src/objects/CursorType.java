package objects;

public enum CursorType {
	ZONE_EMPTY(TileType.EMPTY),
	ZONE_RESIDENTIAL(TileType.RESIDENTIAL),
	ZONE_COMMERCIAL(TileType.COMMERCIAL),
	ZONE_INDUSTRIAL(TileType.INDUSTRIAL);
	
	TileType tileType;
	CursorType(TileType tileType) {
		this.tileType = tileType;
	}
	
	public TileType getTileType() {
		return tileType;
	}
}
