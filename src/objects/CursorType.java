package objects;

/**
 * Used to indicate the current cursor in use.
 * @author Josh
 */
public enum CursorType {
	ZONE_EMPTY(null, CursorIndicator.EMPTY),
	ZONE_RESIDENTIAL(TileType.RESIDENTIAL, CursorIndicator.RESIDENTIAL),
	ZONE_COMMERCIAL(TileType.COMMERCIAL, CursorIndicator.COMMERCIAL),
	ZONE_INDUSTRIAL(TileType.INDUSTRIAL, CursorIndicator.INDUSTRIAL),
	ZONE_BULLDOZE(TileType.EMPTY, CursorIndicator.BULLDOZE);
	
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
	
	/**
	 * Indicates if the tile type exists. This is generally used to tell if the cursor is "Empty Hand".
	 * If the tile type does not exist, then we are using "Empty Hand". 
	 * @return boolean
	 */
	public boolean isTileTypeExists() {
		return tileType != null ? true : false;
	}
}  
