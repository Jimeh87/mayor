package specification.property.building;

import specification.property.zone.ZoneType;
import economy.Product;

public class FarmSpecification extends BuildingSpecification {

	public FarmSpecification() {
		super(BuildingType.FARM, ZoneType.AGRICULTURAL);
		productForSaleMap.put(Product.FOOD, 0);
	}

	@Override
	public void tick() {
		productForSaleMap.put(Product.FOOD, productForSaleMap.get(Product.FOOD) + 1);
	}
}
