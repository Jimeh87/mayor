package specification.property.building.concrete;

import java.util.Iterator;
import java.util.Map.Entry;

import specification.property.building.BuildingType;
import specification.property.building.GeneratedBuildingSpecification;
import specification.property.zone.ZoneType;
import economy.Product;
import economy.ProductQuantityMaxStorageWrapper;

public class BasicHomeSpecification extends GeneratedBuildingSpecification {

	public BasicHomeSpecification() {
		super(BuildingType.RES_SMALL, ZoneType.RESIDENTIAL);
	}
	
	@Override 
	public String getName() {
		return "Basic home";
	}
	
	@Override
	public void tick() {
		if (this.getPerson() != null) {
			for (Iterator<Entry<Product, Integer>> i = getPerson().getProductDemand().iterator();
					i.hasNext();) {
				Entry<Product, Integer> productDemandEntry = i.next();
				this.getProductDemand().setQuantityForProduct(productDemandEntry.getKey(), productDemandEntry.getValue());
			}
		}
	}

	@Override
	public ProductQuantityMaxStorageWrapper getInitialProductForSale() {
		return new ProductQuantityMaxStorageWrapper();
	}
}
