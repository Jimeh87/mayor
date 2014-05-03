package objects;

public class Grid {
	Grid(int xSize, int ySize) {
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
	
	
	
}
