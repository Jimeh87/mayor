package objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to track tiles
 * @author Jim
 */
public class Grid {
	public Grid(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.tileArray = initializeGrid();
	}
	
	int xSize;
	int ySize;
	
	Tile[][] tileArray;
	

	/**
	 * initializes the grid
	 * @return
	 */
	private Tile[][] initializeGrid() {
		tileArray = new Tile[xSize][ySize];
		
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				tileArray[x][y] = new Tile(x, y);
			}
		}
		
		return tileArray;
	}
	
	public Tile getTile(int xLocation, int yLocation) {
		return tileArray[xLocation][yLocation];
	}
	
	/**
	 * Turns the 2-D tileArray into a 1-D tileArray
	 * TODO: This needs to be rethought...
	 * @return tileArray
	 */
	public List<Tile> getTileList() {
		List<Tile> tileList = new ArrayList<Tile>();
		
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				tileList.add(tileArray[x][y]);
			}
		}
		return tileList;
	}

	public int getXSize() {
		return xSize;
	}

	public void setXSize(int xSize) {
		this.xSize = xSize;
	}

	public int getYSize() {
		return ySize;
	}

	public void setYSize(int ySize) {
		this.ySize = ySize;
	}
	
	/**
	 * Just test code here for now
	 */
	public void tick() {
		List<Tile> tileList = getTileList();
		for (Tile tile : tileList) {
			if (tile.getTileType() != TileType.EMPTY) {
				tile.setBuildingIdSubType(0);
				tile.refreshPane();
			}
		}
	}

}
