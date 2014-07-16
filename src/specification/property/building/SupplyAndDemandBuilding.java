package specification.property.building;

import economy.ProductQuantityMaxStorageWrapper;
import economy.ProductQuantityWrapper;

public interface SupplyAndDemandBuilding {
	public ProductQuantityMaxStorageWrapper getProductForSale();
	public ProductQuantityWrapper getProductDemand();
	public ProductQuantityMaxStorageWrapper getInitialProductForSale();
}
