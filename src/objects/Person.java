package objects;

import specification.SpecificationEntity;
import specification.property.PropertySpecification;
import economy.Product;
import economy.ProductQuantityWrapper;

public class Person {
	
	public Person() {
		productDemand = new ProductQuantityWrapper();
		productDemand.setQuantityForProduct(Product.FOOD, 1);
	}
	
	private SpecificationEntity<PropertySpecification> homeEntity = null;
	private ProductQuantityWrapper productDemand;

	public SpecificationEntity<PropertySpecification> getHomeEntity() {
		return homeEntity;
	}

	public void setHomeEntity(SpecificationEntity<PropertySpecification> homeEntity) {
		this.homeEntity = homeEntity;
	}

	public ProductQuantityWrapper getProductDemand() {
		return productDemand;
	}
}
