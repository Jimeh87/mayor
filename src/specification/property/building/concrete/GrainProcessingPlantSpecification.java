package specification.property.building.concrete;

import specification.property.building.BuildingType;
import specification.property.building.GeneratedBuildingSpecification;
import specification.property.zone.ZoneType;
import economy.Product;
import economy.ProductQuantityMaxStorageWrapper;

public class GrainProcessingPlantSpecification extends GeneratedBuildingSpecification {

	public GrainProcessingPlantSpecification() {
		super(BuildingType.IND_SMALL, ZoneType.INDUSTRIAL);
	}
	
	@Override
	public void tick() {
		productForSale.incrementQuantityForProduct(Product.PROCESSED_FOOD, 1);
		getProductDemand().setQuantityForProduct(Product.UNPROCESSED_FOOD, 2);
	}

	@Override
	public String getName() {
		return "Grain processing plant";
	}

	@Override
	public ProductQuantityMaxStorageWrapper getInitialProductForSale() {
		ProductQuantityMaxStorageWrapper initProductForSale = new ProductQuantityMaxStorageWrapper();
		initProductForSale.setQuantityAndMaxQuantityForProduct(Product.PROCESSED_FOOD, 0, 10);
		return initProductForSale;
	}
}
