import java.util.ArrayList;
import java.util.List;

import objects.Grid;
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


	public ArrayList<Tile> processTick() {
		
		return null;
	}
}
