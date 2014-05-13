package objects;

import java.util.ArrayList;
import java.util.List;

import property.Property;
import property.specification.TileSpecification;

/**
 * Used to track tiles
 * @author Jim
 */
public class Grid {
	public Grid(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.propertyArray = initializeGrid();
	}
	
	int xSize;
	int ySize;
	
	Property[][] propertyArray;
	

	/**
	 * initializes the grid
	 * @return
	 */
	private Property[][] initializeGrid() {
		propertyArray = new Property[xSize][ySize];
		
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				Property property = new Property();
				property.addSpecification(new TileSpecification(new Tile(x, y)));
				propertyArray[x][y] = property;
			}
		}
		
		return propertyArray;
	}
	
	public Property getProperty(int xLocation, int yLocation) {
		return propertyArray[xLocation][yLocation];
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

}
