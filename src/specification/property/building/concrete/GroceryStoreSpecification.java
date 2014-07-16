package specification.property.building.concrete;

import specification.property.building.BuildingType;
import specification.property.building.GeneratedBuildingSpecification;
import specification.property.zone.ZoneType;
import economy.Product;
import economy.ProductQuantityMaxStorageWrapper;

public class GroceryStoreSpecification extends GeneratedBuildingSpecification {

	public GroceryStoreSpecification() {
		super(BuildingType.COM_SMALL, ZoneType.COMMERCIAL);
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
	
	@Override
	public ProductQuantityMaxStorageWrapper getInitialProductForSale() {
		ProductQuantityMaxStorageWrapper initProductForSale = new ProductQuantityMaxStorageWrapper();
		initProductForSale.setQuantityAndMaxQuantityForProduct(Product.FOOD, 0, 10);
		return initProductForSale;
	}
}
