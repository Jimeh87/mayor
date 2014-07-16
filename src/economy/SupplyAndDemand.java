package economy;

import grid.Grid;

import java.util.HashMap;
import java.util.Iterator;

import specification.SpecificationEntity;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.building.SupplyAndDemandBuilding;


public class SupplyAndDemand {

	public SupplyAndDemand(Grid<PropertySpecification> propertyGrid) {
		this.pGrid = propertyGrid;
	}

	ProductQuantityWrapper maxSupply = new ProductQuantityWrapper();
	ProductQuantityWrapper demand = new ProductQuantityWrapper();
	private Grid<PropertySpecification> pGrid;
	
	public ProductQuantityWrapper getMaxSupply() {
		return maxSupply;
	}
	public ProductQuantityWrapper getDemand() {
		return demand;
	}
	
	public void refreshMaxSupply() {
		maxSupply = new ProductQuantityWrapper();
		for (Iterator<SpecificationEntity<PropertySpecification>> it = pGrid.iterator();
				it.hasNext();) {
			SpecificationEntity<PropertySpecification> propertyEntity = it.next();
			SupplyAndDemandBuilding buildingSpec = (SupplyAndDemandBuilding) propertyEntity.getSpecificationOfType(PropertySpecificationType.SUPPLY_AND_DEMAND_BUILDING);
			if (buildingSpec != null) {
				maxSupply.incrementalMergeForMaxProduct(buildingSpec.getProductForSale());
			}
		}
	}
	
	public Double getProductSupplyDemandRatio(Product product) {

		Double demand = (double) this.demand.getQuantityForProduct(product);
		Double supply = (double) maxSupply.getQuantityForProduct(product);
		if (supply == 0) {
			supply = .95;
		}
		
		Double rate = (Math.log(demand / supply));
		
		return rate + .2;
	}
	
	public HashMap<Product, Double> getAllProductSupplyDemandRatio() {
		HashMap<Product, Double> productSupplyDemandRatio = new HashMap<Product, Double>();
		for (Product product : Product.values()) {
			productSupplyDemandRatio.put(product, getProductSupplyDemandRatio(product));
		}
		return productSupplyDemandRatio;
	}
}
