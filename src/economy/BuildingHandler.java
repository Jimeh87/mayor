package economy;

import grid.Grid;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import specification.property.PropertySpecification;

public class BuildingHandler {

	public BuildingHandler(Grid<PropertySpecification> propertyGrid, SupplyAndDemand supplyAndDemand) {
		this.pGrid = propertyGrid;
		this.supplyAndDemand = supplyAndDemand;
	}
	
	private SupplyAndDemand supplyAndDemand;
	private Grid<PropertySpecification> pGrid;
	
	//private productBuilding = productBuilding;
	
	public void generateBuilding() {
		Product product = getProductWithHighestPositiveRatio();
	}
	
	private Product getProductWithHighestPositiveRatio() { //TODO: This will be replaced with random
		supplyAndDemand.refreshMaxSupply();
		HashMap<Product, Double> productSupplyDemandRatio = supplyAndDemand.getAllProductSupplyDemandRatio();
		Entry<Product, Double> highestEntry = null;
		for (Iterator<Entry<Product, Double>> it = productSupplyDemandRatio.entrySet().iterator();
				it.hasNext();) {
			
			Entry<Product, Double> productSupplyDemandRatioEntry = it.next();
			if (highestEntry == null || highestEntry.getValue() < productSupplyDemandRatioEntry.getValue()) {
				highestEntry = productSupplyDemandRatioEntry;
			}
			
		}
		
		if (highestEntry.getValue() < 0) {
			return null;
		}
		return highestEntry.getKey();
	}

}
