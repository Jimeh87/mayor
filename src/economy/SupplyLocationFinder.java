package economy;

import grid.Grid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import objects.MayorUtil;
import objects.Tile;
import specification.SpecificationEntity;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.TileSpecification;
import specification.property.building.SupplyAndDemandBuilding;
import specification.property.zone.ZoneSpecification;
import specification.property.zone.ZoneType;

/**
 * @author Jim
 * TODO: currently this handles the DemandHandler... this isn't correct. Should be the object that calls this object.
 */
public class SupplyLocationFinder {

	public SupplyLocationFinder(Grid<PropertySpecification> pGrid, ProductQuantityWrapper demand) {
		this.pGrid = pGrid;
		this.demand = demand;
	}
	
	private Grid<PropertySpecification> pGrid;
	private ProductQuantityWrapper demand;
	
	private Boolean allSuppliesFound = null;
	
	public HashMap<Product, List<SpecificationEntity<PropertySpecification>>> findProducts(SpecificationEntity<PropertySpecification> requestPropertyEntity, ProductQuantityWrapper productsToPurchase) {
		allSuppliesFound = true;
		HashMap<Product, List<SpecificationEntity<PropertySpecification>>> productPropertyLocationMap = new HashMap<Product, List<SpecificationEntity<PropertySpecification>>>();
		for (Iterator<Entry<Product, Integer>> it = productsToPurchase.iterator();
				it.hasNext();) {
			Entry<Product, Integer> productsToPurchaseEntry = it.next();		
			List<SpecificationEntity<PropertySpecification>> supplierPropertyList = getSupplierList(requestPropertyEntity, productsToPurchaseEntry.getKey(), productsToPurchaseEntry.getValue());
			productPropertyLocationMap.put(productsToPurchaseEntry.getKey(), supplierPropertyList);
		}
		
		demand.incrementalMerge(productsToPurchase); //TODO: Am I doing this in the right class??
		
		return productPropertyLocationMap;
		
	}

	private List<SpecificationEntity<PropertySpecification>> getSupplierList(
			SpecificationEntity<PropertySpecification> requestPropertyEntity,
			Product product, Integer quantityNeeded) {
		
		ZoneSpecification requestZoneSpec = (ZoneSpecification) requestPropertyEntity.getSpecificationOfType(PropertySpecificationType.ZONE);
		List<ZoneType> suppliersForRequestedZoneType = ZoneType.getAvailableSuppliersForZoneTypeList(requestZoneSpec.getZoneType()); //assuming ZoneType will not be null. Anything making a request MUST have a ZoneType.
		
		PriorityQueue<SpecificationEntity<PropertySpecification>> supplierPriorityQueue = new PriorityQueue<SpecificationEntity<PropertySpecification>>(new SupplierComparator(requestPropertyEntity, product, quantityNeeded));
		for  (Iterator<SpecificationEntity<PropertySpecification>> i = pGrid.iterator(); 
				i.hasNext();) {
			SpecificationEntity<PropertySpecification> propertyEntity = i.next();
			ZoneSpecification zoneSpec = (ZoneSpecification) propertyEntity.getSpecificationOfType(PropertySpecificationType.ZONE);
			SupplyAndDemandBuilding buildingSpec = (SupplyAndDemandBuilding) propertyEntity.getSpecificationOfType(PropertySpecificationType.SUPPLY_AND_DEMAND_BUILDING);
			if (zoneSpec != null && suppliersForRequestedZoneType.contains(zoneSpec.getZoneType())) {
				if (buildingSpec != null && buildingSpec.getProductForSale().containsProduct(product)) {
					supplierPriorityQueue.add(propertyEntity);
				}
			}
		}
		
		List<SpecificationEntity<PropertySpecification>> supplierPropertyList = new ArrayList<SpecificationEntity<PropertySpecification>>();
		int currentQuantity = 0;
		for  (Iterator<SpecificationEntity<PropertySpecification>> i = supplierPriorityQueue.iterator();
				(i.hasNext() && currentQuantity >= quantityNeeded);) {
			SpecificationEntity<PropertySpecification> propertyEntity = i.next();
			SupplyAndDemandBuilding buildingSpec = (SupplyAndDemandBuilding) propertyEntity.getSpecificationOfType(PropertySpecificationType.SUPPLY_AND_DEMAND_BUILDING);
			currentQuantity += buildingSpec.getProductForSale().getQuantityForProduct(product);
			
			supplierPropertyList.add(propertyEntity);
		}
		
		if (quantityNeeded > currentQuantity) { //didn't meet criteria for product needed
			allSuppliesFound = false;
		}
		
		return supplierPropertyList;
	}
	
	public Boolean isAllSuppliesFound() {
		return allSuppliesFound;
	}
	
}



class SupplierComparator implements Comparator<SpecificationEntity<PropertySpecification>> {

	SupplierComparator(SpecificationEntity<PropertySpecification> requestProperty, Product product, Integer quantityRequired) {
		this.requestProperty = requestProperty;
		this.product = product;
		this.quantityRequired = quantityRequired;
	}
	
	private SpecificationEntity<PropertySpecification> requestProperty;
	private Product product;
	private Integer quantityRequired;
	
	@Override
	public int compare(SpecificationEntity<PropertySpecification> p1, SpecificationEntity<PropertySpecification> p2) {

		SupplyAndDemandBuilding buildingSpec1 = (SupplyAndDemandBuilding) p1.getSpecificationOfType(PropertySpecificationType.SUPPLY_AND_DEMAND_BUILDING);
		SupplyAndDemandBuilding buildingSpec2 = (SupplyAndDemandBuilding) p2.getSpecificationOfType(PropertySpecificationType.SUPPLY_AND_DEMAND_BUILDING);
		
		if (buildingSpec1 == null && buildingSpec2 != null) {
			return 1;
		} else if (buildingSpec1 != null && buildingSpec2 == null) {
			return -1;
		} else if (buildingSpec1 != null && buildingSpec2 != null) {
			Integer quantity1 = buildingSpec1.getProductForSale().getQuantityForProduct(product);
			Integer quantity2 = buildingSpec2.getProductForSale().getQuantityForProduct(product);
			Tile tile1 = ((TileSpecification) p1.getSpecificationOfType(PropertySpecificationType.TILE)).getTile();
			Tile tile2 = ((TileSpecification) p2.getSpecificationOfType(PropertySpecificationType.TILE)).getTile();
			
			return compareQuantity(tile1, quantity1, tile2, quantity2);
		}
		
		return 0;
	}
	
	private int compareQuantity(Tile tile1, Integer quantity1, Tile tile2, Integer quantity2) {
		if (quantity1 == null && quantity2 != null) {
			return 1;
		} else if (quantity1 != null && quantity2 == null) {
			return -1;
		} else if (quantity1 != null && quantity2 != null) {
			if (quantity1 >= quantityRequired && quantity2 >= quantityRequired) {
				return compareDistance(tile1, tile2);
			} else if (quantity1 == quantity2) {
				return compareDistance(tile1, tile2);
			} else {
				return quantity1 - quantity2;
			}
		}
		
		return 0;
	}
	
	private int compareDistance(Tile tile1, Tile tile2) {
		Tile requestTile = ((TileSpecification) requestProperty.getSpecificationOfType(PropertySpecificationType.TILE)).getTile();
		
		double distance1 = MayorUtil.calculateDistance(requestTile, tile1);
		double distance2 = MayorUtil.calculateDistance(requestTile, tile2);
		
		return (int) (distance1 - distance2);
	}
}