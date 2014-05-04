import java.util.ArrayList;
import java.util.List;

import objects.Grid;
import objects.Residential;
import objects.Tile;


public class City {
	
	List<Tile> tileList;
	public City(int xSize, int ySize) {
		grid = new Grid(xSize, ySize);
	}

	private Grid grid;
	
	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public List<Tile> processTick() {
		grid.setTile(0, 0, new Residential(0, 0, 1));
		List<Tile> updateList = new ArrayList<Tile>();
		updateList.add(grid.getTile(0,  0));
		return updateList;
	}
}
