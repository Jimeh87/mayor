package economy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class ProductQuantityWrapper {

	public ProductQuantityWrapper() {
		productQuantity = new HashMap<Product, Integer>();
	}
	
	private HashMap<Product, Integer> productQuantity;
	
	public boolean containsProduct(Product product) {
		return productQuantity.containsKey(product);
	}
	public Integer getQuantityForProduct(Product product) {
		if (productQuantity.get(product) == null) {
			return 0;
		}
		return productQuantity.get(product);
	}
	
	public Iterator<Entry<Product, Integer>> iterator() {
		return productQuantity.entrySet().iterator();
	}
	
	public void setQuantityForProduct(Product product, Integer quantity) {
		productQuantity.put(product, quantity);
	}
	
	public void incrementQuantityForProduct(Product product, Integer quantity) {
		productQuantity.put(product, getQuantityForProduct(product) +  quantity);
	}
	
	public void incrementalMerge(ProductQuantityWrapper productQuantity) {
		for (Iterator<Entry<Product, Integer>> it = productQuantity.iterator();
				it.hasNext();) {
			Entry<Product, Integer> productQuantityEntry = it.next();
			incrementQuantityForProduct(productQuantityEntry.getKey(), productQuantityEntry.getValue());
		}
	}
	
	@SuppressWarnings("unused")
	private void debug() {
		System.out.println("Product: Quantity");
		for (Product product : Product.values()) {
			System.out.println(product + ": " + productQuantity.get(product));
		}
	}
}
