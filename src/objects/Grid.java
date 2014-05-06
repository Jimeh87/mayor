package objects;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	public Grid(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.tileArray = initializeGrid();
	}
	
	int xSize;
	int ySize;
	
	Tile[][] tileArray;
	
	private Tile[][] initializeGrid() {
		tileArray = new Tile[xSize][ySize];
		
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				tileArray[x][y] = new Tile(x, y, TileType.EMPTY);
			}
		}
		
		return tileArray;
	}
	
	public Tile getTile(int xLocation, int yLocation) {
		return tileArray[xLocation][yLocation];
	}

	public void setTile(int xLocation, int yLocation, Tile tile) {
		tileArray[xLocation][yLocation] = tile;
	}
	
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
