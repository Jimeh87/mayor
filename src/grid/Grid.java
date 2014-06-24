package grid;

import java.util.ArrayList;
import java.util.List;

import specification.Specification;
import specification.SpecificationEntity;

/**
 * Used to track tiles
 */
public class Grid<T extends Specification> {
	public Grid(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.specificationEntityList = initializeGrid();
	}
	
	int xSize;
	int ySize;
	
	final List<ArrayList<SpecificationEntity<T>>> specificationEntityList;	

	private List<ArrayList<SpecificationEntity<T>>> initializeGrid() {
		final List<ArrayList<SpecificationEntity<T>>> specificationEntityList = new ArrayList<ArrayList<SpecificationEntity<T>>>();
		for (int x = 0; x < xSize; x++) {
			specificationEntityList.add(new ArrayList<SpecificationEntity<T>>());
			for (int y = 0; y < ySize; y++) {
				specificationEntityList.get(x).add(new SpecificationEntity<T>());
			}
		}
		
		return specificationEntityList;
	}
	
	public GridIterator<T> iterator() {
		return new GridIterator<T>(this);
	}
	
	/*
	private SpecificationEntity<T>[][] initializeGrid() {
		@SuppressWarnings("unchecked")
		propertyArray = Array.newInstance(SpecificationEntity.class, xSize, ySize);
		
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				Property property = new Property();
				property.addSpecification(new TileSpecification(new Tile(x, y)));
				propertyArray[x][y] = property;
			}
		}
		
		return propertyArray;
	}*/
	
	public SpecificationEntity<T> getSpecificationEntity(int xLocation, int yLocation) {
		return specificationEntityList.get(xLocation).get(yLocation);
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
