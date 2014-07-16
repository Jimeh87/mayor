package specification.property.building.concrete;

import specification.property.building.BuildingSpecification;
import specification.property.building.BuildingType;
import specification.property.zone.ZoneType;
import economy.Product;

public class GroceryStoreSpecification extends BuildingSpecification implements GeneratedBuilding {

	public GroceryStoreSpecification() {
		super(BuildingType.COM_SMALL, ZoneType.COMMERCIAL);
		productForSale = initProductForSale.clone();
	}

	static {
		initProductForSale.setMaxQuantityForProduct(Product.FOOD, 10);
		initProductForSale.setQuantityForProduct(Product.FOOD, 0);
	}
	
	@Override
	public void tick() {
		productForSale.incrementQuantityForProduct(Product.FOOD, 1); //TODO: hack
		getProductDemand().setQuantityForProduct(Product.PROCESSED_FOOD, 1);
	}

	@Override
	public String getName() {
		return "Grocery store";
	}
}
