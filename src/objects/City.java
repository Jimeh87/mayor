package objects;

import economy.Economy;
import grid.Grid;

import java.util.Iterator;
import java.util.List;

import specification.SpecificationEntity;
import specification.desirability.DesirabilitySpecification;
import specification.property.PropertySpecification;

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
		this.grid = new Grid<PropertySpecification>(xSize, ySize);
		economy = new Economy(grid, new Grid<DesirabilitySpecification>(xSize, ySize));
	}

	private Grid<PropertySpecification> grid;
	Economy economy;
	/**
	 * Gets the grid of the city
	 * @return grid
	 */
	public Grid<PropertySpecification> getGrid() {
		return grid;
	}
	public Economy getEconomy() {
		return economy;
	}

	/**
	 * Tick for everything in the city. This will call grid.tick()
	 */
	public void tick() {
		for (Iterator<SpecificationEntity<PropertySpecification>> i = grid.iterator();
				i.hasNext();) {
			List<PropertySpecification> propertySpecificationList = i.next().getSpecificationList();
			for (PropertySpecification propertySpecification : propertySpecificationList) {
				propertySpecification.tick();
			}
		}
		
		economy.tick();
	}
}
