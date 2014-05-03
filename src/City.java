import java.util.ArrayList;

import objects.Grid;
import objects.Tile;
import objects.TileType;


public class City {
	public City(int xSize, int ySize) {
		generateTiles(xSize, ySize);
		grid = new Grid(xSize, ySize);
		
	}
	
	public initializeTiles(int xSize, int ySize) {
		
		List<Tile> tileList = 
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				tileArray[x][y] = new Tile(x, y, TileType.EMPTY);
			}
		}
		
		return tileArray;
	}

	private Grid grid;
	
	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public ArrayList<Tile> processTick() {
		
		return null;
	}
}
