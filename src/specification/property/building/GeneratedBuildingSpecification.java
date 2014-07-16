package specification.property.building;

import economy.ProductQuantityMaxStorageWrapper;
import economy.ProductQuantityWrapper;
import specification.property.zone.ZoneType;

public abstract class GeneratedBuildingSpecification extends BuildingSpecification implements SupplyAndDemandBuilding {
	public GeneratedBuildingSpecification(BuildingType buildingType, ZoneType zoneType) {
		super(buildingType, zoneType);
		productForSale = getInitialProductForSale().clone();
	}
	
	public ProductQuantityMaxStorageWrapper productForSale = new ProductQuantityMaxStorageWrapper();
	public ProductQuantityWrapper productDemand = new ProductQuantityWrapper();
	
	@Override
	public ProductQuantityMaxStorageWrapper getProductForSale() {
		return productForSale;
	}

	@Override
	public ProductQuantityWrapper getProductDemand() {
		return productDemand;
	}
}
