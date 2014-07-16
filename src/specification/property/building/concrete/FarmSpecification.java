package specification.property.building.concrete;

import specification.property.building.BuildingSpecification;
import specification.property.building.BuildingType;
import specification.property.zone.ZoneType;
import economy.Product;

public class FarmSpecification extends BuildingSpecification implements GeneratedBuilding {

	public FarmSpecification() {
		super(BuildingType.FARM, ZoneType.AGRICULTURAL);
		productForSale = initProductForSale.clone();
	}
	
	static {
		initProductForSale.setMaxQuantityForProduct(Product.UNPROCESSED_FOOD, 5);
		initProductForSale.setQuantityForProduct(Product.UNPROCESSED_FOOD, 0);
	}

	@Override
	public void tick() {
		productForSale.incrementQuantityForProduct(Product.UNPROCESSED_FOOD, 5);
	}

	@Override
	public String getName() {
		return "Farm";
	}
}
