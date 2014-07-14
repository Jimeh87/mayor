package economy;

import grid.Grid;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import objects.Tile;
import specification.SpecificationEntity;
import specification.SpecificationType;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.TileSpecification;
import specification.property.building.BuildingSpecification;
import specification.property.building.BuildingSpecificationType;
import specification.property.zone.ZoneSpecification;

public class BuildingHandler {

	public BuildingHandler(Grid<PropertySpecification> propertyGrid, SupplyAndDemand supplyAndDemand) {
		this.pGrid = propertyGrid;
		this.supplyAndDemand = supplyAndDemand;
		
		productBuilding = new HashMap<Product, SpecificationType>();
		for (Product product : Product.values()) {
			for (SpecificationType specificationType : BuildingSpecificationType.values()) {
				try {
					BuildingSpecification buildingSpec = (BuildingSpecification) specificationType.getSpecificationClass().newInstance();
					if (buildingSpec.getProductForSale().containsProduct(product)) {
						productBuilding.put(product, specificationType);
					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private SupplyAndDemand supplyAndDemand;
	private Grid<PropertySpecification> pGrid;
	private HashMap<Product, SpecificationType> productBuilding; //FOR NOW ONLY ONE BUILDING MAKES ONE PRODUCT
	
	public void generateBuilding() {
		//TODO needs to get list of buildings incase first one does not work. PriorityQueue!!!
		Product product = getProductWithHighestPositiveRatio();
		if (product == null) {
			return;
		}
		SpecificationType specType = productBuilding.get(product);
		if (specType == null) {
			throw new IllegalStateException("No building types exist to produce: " + product + "... Most likely class didn't get added to BuildingSpecificationType");
		}
		
		try {
			BuildingSpecification newBuildingSpec = (BuildingSpecification) specType.getSpecificationClass().newInstance();
			newBuildingSpec.getZoneType();
			for (Iterator<SpecificationEntity<PropertySpecification>> it = pGrid.iterator();
					it.hasNext();) {
				SpecificationEntity<PropertySpecification> propertyEntity = it.next();
				BuildingSpecification buildingSpec = (BuildingSpecification) propertyEntity.getSpecificationOfType(PropertySpecificationType.BUILDING);
				ZoneSpecification zoneSpec = (ZoneSpecification) propertyEntity.getSpecificationOfType(PropertySpecificationType.ZONE);
				if (buildingSpec == null
						&& zoneSpec != null && zoneSpec.getZoneType() == newBuildingSpec.getZoneType()) {
					propertyEntity.addSpecification(newBuildingSpec);
					Tile tile = ((TileSpecification) propertyEntity.getSpecificationOfType(PropertySpecificationType.TILE)).getTile();
					tile.setPaneId(newBuildingSpec.getPaneId());
					tile.refreshPane();
					System.out.println("BUILDING GENERATED OF TYPE: " + newBuildingSpec.getName());
					break; //hate doing this... when this is done properly this will not be here.
				}
				
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
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
