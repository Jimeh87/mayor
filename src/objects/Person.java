package objects;

import java.util.HashMap;

import economy.Product;
import specification.SpecificationEntity;
import specification.property.PropertySpecification;

public class Person {
	
	public Person() {
		productDemandMap = new HashMap<Product, Integer>();
		productDemandMap.put(Product.FOOD, 1);
	}
	
	private SpecificationEntity<PropertySpecification> homeEntity = null;
	private HashMap<Product, Integer> productDemandMap;

	public SpecificationEntity<PropertySpecification> getHomeEntity() {
		return homeEntity;
	}

	public void setHomeEntity(SpecificationEntity<PropertySpecification> homeEntity) {
		this.homeEntity = homeEntity;
	}

	public HashMap<Product, Integer> getProductDemandMap() {
		return productDemandMap;
	}

	public void setProductDemandMap(HashMap<Product, Integer> productDemand) {
		this.productDemandMap = productDemand;
	}
}
