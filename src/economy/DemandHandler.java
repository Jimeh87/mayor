package economy;

import java.util.HashMap;

import objects.Constants;

public class DemandHandler {

	private static double DEMAND_MODIFIER = (1 / (double)(Constants.GRID_X + Constants.GRID_Y) );
	
	public DemandHandler() {
		productDemand = new HashMap<Product, Double>();
		for (Product product : Product.values()) {
			productDemand.put(product, (double) 0);
		}
	}
	
	private HashMap<Product, Double> productDemand;
	
	public Double getDemandForProduct(Product product) {
		return productDemand.get(product);
	}
	
	//demand calculation needs some work
	public void incrementDemandForProduct(Product product, Integer demand) {
		productDemand.put(product, productDemand.get(product) +  DEMAND_MODIFIER);
	}
	
	protected void tick() {
		//reduce demand for product over time. 
		for (Product product : Product.values()) {
			if (productDemand.get(product) > 0) {
				productDemand.put(product, productDemand.get(product) - DEMAND_MODIFIER);
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
