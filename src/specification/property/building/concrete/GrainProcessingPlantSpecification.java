package specification.property.building.concrete;

import specification.property.building.BuildingSpecification;
import specification.property.building.BuildingType;
import specification.property.building.GeneratedBuilding;
import specification.property.zone.ZoneType;
import economy.Product;

public class GrainProcessingPlantSpecification extends BuildingSpecification implements GeneratedBuilding {

	public GrainProcessingPlantSpecification() {
		super(BuildingType.IND_SMALL, ZoneType.INDUSTRIAL);
		productForSale = initProductForSale.clone();
	}

	static {
		initProductForSale.setMaxQuantityForProduct(Product.PROCESSED_FOOD, 10);
		initProductForSale.setQuantityForProduct(Product.PROCESSED_FOOD, 0);
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
}
