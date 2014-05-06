package objects;

import javafx.scene.layout.Pane;

/**
 * Contains all the information for a tile displayed on the screen including the pane.
 * @author Jim
 *
 */
public class Tile {
	
	
	/**
	 * Creates a tile with location x, y
	 * @param xLocation
	 * @param yLocation
	 */
	public Tile(int xLocation, int yLocation) {
		this(xLocation, yLocation, TileType.EMPTY);
	}
	private Tile(int xLocation, int yLocation, TileType tileType) {
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
	
	private Integer buildingIdSubType = null;
	
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
	/**
	 * gets the tileType of the current tile
	 * @return tileType
	 */
	public TileType getTileType() {
		return tileType;
	}
	/**
	 * Sets the tileType of the current tile. It will also reset the sub BuildingIdSubType
	 * @param tileType
	 */
	public void setTileType(TileType tileType) {
		this.tileType = tileType;
		setBuildingIdSubType(null); //reset tile sub type
	}
	/**
	 * Returns the pane associated with the tile.
	 * @return
	 */
	public Pane getPane() {
		return pane;
	}
	
	/**
	 * Sets the Building Id Sub Type
	 * @param buildingIdSubType
	 */
	public void setBuildingIdSubType(Integer buildingIdSubType) {
		this.buildingIdSubType = buildingIdSubType;
	}
	
	/**
	 * Returns the buildingIdSubType as a String, where null is an empty String.
	 * @return 
	 */
	private String getBuildingIdSubTypeAsString() {
		return buildingIdSubType != null ? buildingIdSubType.toString() : "";
	}
	
	/**
	 * If there is a building on this tile, it will return true
	 * @return
	 */
	public boolean isBuildingExists() {
		return buildingIdSubType != null ? true : false;
	}
	
	/**
	 * refreshes the pane associated with the tile with current Tile data.
	 */
	public void refreshPane() {
		pane.setId(tileType.getBuildingIdMainType() + getBuildingIdSubTypeAsString());
	}
}
