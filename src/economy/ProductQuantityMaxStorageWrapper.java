package economy;

import java.util.HashMap;

public class ProductQuantityMaxStorageWrapper extends ProductQuantityWrapper {
	public ProductQuantityMaxStorageWrapper() {
		super();
		productMaxQuantity = new HashMap<Product, Integer>();
	}
	private HashMap<Product, Integer> productMaxQuantity;
	
	public Integer getQuantityForProduct(Product product) {
		if (productQuantity.get(product) == null) {
			return 0;
		}
		return productQuantity.get(product);
	}
	
	@Override
	public void setQuantityForProduct(Product product, Integer quantity) {
		quantity = checkQuantity(product, quantity);
		super.setQuantityForProduct(product, quantity);
	}
	
	public void setMaxQuantityForProduct(Product product, Integer maxQuantity) {
		productMaxQuantity.put(product, maxQuantity);
	}
	
	public void setQuantityAndMaxQuantityForProduct(Product product, Integer quantity, Integer maxQuantity) {
		setMaxQuantityForProduct(product, maxQuantity);
		setQuantityForProduct(product, quantity);
	}
	
	public Integer getMaxQuantityForProduct(Product product) {
		return productMaxQuantity.get(product);
	}
	
	@Override
	public void incrementQuantityForProduct(Product product, Integer quantity) {
		quantity = checkQuantity(product, getQuantityForProduct(product) + quantity);
		setQuantityForProduct(product, quantity);
	}
	
	private Integer checkQuantity(Product product, Integer quantity) {
		Integer maxQuantity = productMaxQuantity.get(product);
		if (maxQuantity == null) {
			throw new IllegalStateException("Max quantity must be set before quantity can be set");
		}
		if (quantity > maxQuantity) {
			quantity = maxQuantity;
		}
		return quantity;
	}
	
	public ProductQuantityMaxStorageWrapper clone() {
		return (ProductQuantityMaxStorageWrapper) super.clone();
	}
}
