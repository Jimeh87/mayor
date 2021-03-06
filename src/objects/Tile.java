package objects;

import specification.property.zone.ZoneType;
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
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		pane.setMinSize(32, 32);
		pane.setMaxSize(32,  32);
		overlayPane.setMinSize(32, 32);
		overlayPane.setMaxSize(32, 32);
		refreshPane();
	}
	
	private int xLocation;
	private int yLocation;
	private final Pane pane = new Pane();
	private final Pane overlayPane = new Pane();
	private String paneId = ZoneType.EMPTY.getPaneId();
	
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
	 * Returns the pane associated with the tile.
	 * @return
	 */
	public Pane getPane() {
		return pane;
	}
	
	public Pane getOverlayPane() {
		return overlayPane;
	}
	/**
	 * refreshes the pane associated with the tile with current Tile data.
	 */
	public void refreshPane() {
		if (!lock) {
			pane.setId(getPaneId());
		}
	}
	
	/**
	 * Returns pop up details
	 * @return
	 */
	public String getPopUpDetails() {
		return ("Tile Type: " + getPaneId() + "\n X: " + getXLocation() + " Y: " + getYLocation());
	}
	public String getPaneId() {
		return paneId;
	}
	public void setPaneId(String paneId) {
		this.paneId = paneId;
	}
	
	public void setTempPaneId(String paneId) {
		if (!lock) {
			pane.setId(paneId);
		}
	}
	
	boolean lock = false;
	public void lock() {
		lock = true;
	}
	public void unLock() {
		lock = false;
	}
}
