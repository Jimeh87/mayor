package objects;

public class Tile {
	private int xLocation;
	private int yLocation;
	
	public Tile(int xLocation, int yLocation) {
		this.xLocation = xLocation;
		this.yLocation = yLocation;
	}
	
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
	
	
}
