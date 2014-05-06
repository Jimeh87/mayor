import objects.Grid;

/**
 * @author Jim
 * Main storage of all things city related, including the grid.
 */
public class City {
	
	/**
	 * Constructor for city.
	 * @param xSize x-axis size
	 * @param ySize y-axis size 
	 */
	public City(int xSize, int ySize) {
		grid = new Grid(xSize, ySize);
	}

	private Grid grid;
	
	/**
	 * Gets the grid of the city
	 * @return grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * Tick for everything in the city. This will call grid.tick()
	 */
	public void tick() {
		grid.tick();
	}
}
