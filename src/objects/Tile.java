package objects;

import javafx.scene.layout.Pane;

public class Tile {
	
	
	public Tile(int xLocation, int yLocation) {
		this(xLocation, yLocation, TileType.EMPTY);
	}
	public Tile(int xLocation, int yLocation, TileType tileType) {
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.tileType  = tileType;
		pane.setMinSize(32, 32);
		pane.setMaxSize(32,  32);
	}
	
	private int xLocation;
	private int yLocation;
	final Pane pane = new Pane();

	private TileType tileType;
	
	private Integer desirability = 0;
	
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
	public Pane getPane() {
		return pane;
	}
}
