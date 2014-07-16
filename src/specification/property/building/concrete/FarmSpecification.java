package specification.property.building.concrete;

import specification.property.building.BuildingType;
import specification.property.building.GeneratedBuildingSpecification;
import specification.property.zone.ZoneType;
import economy.Product;
import economy.ProductQuantityMaxStorageWrapper;

public class FarmSpecification extends GeneratedBuildingSpecification {

	public FarmSpecification() {
		super(BuildingType.FARM, ZoneType.AGRICULTURAL);
	}
	
	static {

	}

	@Override
	public void tick() {
		productForSale.incrementQuantityForProduct(Product.UNPROCESSED_FOOD, 5);
	}

	@Override
	public String getName() {
		return "Farm";
	}

	@Override
	public ProductQuantityMaxStorageWrapper getInitialProductForSale() {
		ProductQuantityMaxStorageWrapper initProductForSale = new ProductQuantityMaxStorageWrapper();
		initProductForSale.setQuantityAndMaxQuantityForProduct(Product.UNPROCESSED_FOOD, 0, 5);
		return initProductForSale;
	}
}
