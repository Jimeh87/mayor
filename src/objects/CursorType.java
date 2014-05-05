package objects;

public enum CursorType {
	ZONE_EMPTY(TileType.EMPTY, CursorIndicator.EMPTY),
	ZONE_RESIDENTIAL(TileType.RESIDENTIAL, CursorIndicator.RESIDENTIAL),
	ZONE_COMMERCIAL(TileType.COMMERCIAL, CursorIndicator.COMMERCIAL),
	ZONE_INDUSTRIAL(TileType.INDUSTRIAL, CursorIndicator.INDUSTRIAL);
	
	TileType tileType;
	CursorIndicator cursorIndicator;
	CursorType(TileType tileType, CursorIndicator cursorIndicator) {
		this.tileType = tileType;
		this.cursorIndicator = cursorIndicator;
	}
	
	public TileType getTileType() {
		return tileType;
	}
	
	public CursorIndicator getCursorIndicator(){
		return cursorIndicator;
	}
}  
