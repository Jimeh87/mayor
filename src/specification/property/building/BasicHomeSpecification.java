package specification.property.building;

import java.util.Iterator;
import java.util.Map.Entry;

import specification.property.zone.ZoneType;
import economy.Product;

public class BasicHomeSpecification extends BuildingSpecification {

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
}
