package specification.property.building.concrete;

import specification.property.building.BuildingSpecification;
import specification.property.building.BuildingType;
import specification.property.zone.ZoneType;
import economy.Product;

public class GroceryStoreSpecification extends BuildingSpecification {

	public GroceryStoreSpecification() {
		super(BuildingType.COM_SMALL, ZoneType.COMMERCIAL);
		productForSale.setMaxQuantityForProduct(Product.FOOD, 10);
		productForSale.setQuantityForProduct(Product.FOOD, 0);
	}

	@Override
	public void tick() {
		productForSale.incrementQuantityForProduct(Product.FOOD, 1);
		getProductDemand().setQuantityForProduct(Product.PROCESSED_FOOD, 1);
	}

	@Override
	public String getName() {
		return "Grocery store";
	}
}
