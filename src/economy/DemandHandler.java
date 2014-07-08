package economy;

import java.util.HashMap;

public class DemandHandler {
	public DemandHandler() {
		productDemand = new HashMap<Product, Integer>();
		for (Product product : Product.values()) {
			productDemand.put(product, 0);
		}
	}
	
	private HashMap<Product, Integer> productDemand;
	
	public Integer getDemandForProduct(Product product) {
		return productDemand.get(product);
	}
	
	public void incrementDemandForProduct(Product product, Integer demand) {
		productDemand.put(product, productDemand.get(product) + demand);
	}
	
	protected void tick() {
		
		//reduce demand for product over time. 
		for (Product product : Product.values()) {
			if (productDemand.get(product) > 0) {
				productDemand.put(product, productDemand.get(product) - 1);
			}
		}
		
		//TODO: Remove test
		debug();
	}

	private void debug() {
		System.out.println("CITY DEMAND: ");
		for (Product product : Product.values()) {
			System.out.println(product + ": " + productDemand.get(product));
		}
	}
}
