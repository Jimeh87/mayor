package objects;

public abstract class Tile {
	public Tile(int xLocation, int yLocation) {
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.tileType  = TileType.EMPTY;
	}
	public Tile(int xLocation, int yLocation, TileType tileType) {
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.tileType  = tileType;
	}
	
	private int xLocation;
	private int yLocation;
	
	private TileType tileType;
	
	public int getXLocation() {
		return xLocation;
	}
	public void setXLocation(int xLocation) {
		this.xLocation = xLocation;
	}
	public int getYLocation() {
		return yLocation;
	}
	public void setYLocation(int yLocation) {
		this.yLocation = yLocation;
	}
	public TileType getTileType() {
		return tileType;
	}
	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}
	
	
}
