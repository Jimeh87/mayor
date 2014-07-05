package economy;

import grid.Grid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import objects.MayorUtil;
import objects.Tile;
import specification.SpecificationEntity;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.TileSpecification;
import specification.property.building.BuildingSpecification;
import specification.property.zone.ZoneSpecification;
import specification.property.zone.ZoneType;

public class SupplyRequestHandler {

	public SupplyRequestHandler(Grid<PropertySpecification> pGrid) {
		this.pGrid = pGrid;
	}
	
	private Grid<PropertySpecification> pGrid;
	
	public HashMap<Product, List<SpecificationEntity<PropertySpecification>>> requestProducts(SpecificationEntity<PropertySpecification> requestPropertyEntity, HashMap<Product, Integer> productsToPurchase) {
		
		HashMap<Product, List<SpecificationEntity<PropertySpecification>>> productPropertyLocationMap = new HashMap<Product, List<SpecificationEntity<PropertySpecification>>>();
		for (Iterator<Product> i = productsToPurchase.keySet().iterator(); 
				i.hasNext();) {
			Product product = i.next();
			Integer quantity = productsToPurchase.get(product);
			
			List<SpecificationEntity<PropertySpecification>> supplierPropertyList = getSupplierList(requestPropertyEntity, product, quantity);
			
			productPropertyLocationMap.put(product, supplierPropertyList);
			
		}
		
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
			BuildingSpecification buildingSpec = (BuildingSpecification) propertyEntity.getSpecificationOfType(PropertySpecificationType.BUILDING);
			if (zoneSpec != null && suppliersForRequestedZoneType.contains(zoneSpec.getZoneType())) {
				if (buildingSpec != null && buildingSpec.getProductForSale().get(product) != null) {
					supplierPriorityQueue.add(propertyEntity);
				}
			}
		}
		
		List<SpecificationEntity<PropertySpecification>> supplierPropertyList = new ArrayList<SpecificationEntity<PropertySpecification>>();
		int currentQuantity = 0;
		for  (Iterator<SpecificationEntity<PropertySpecification>> i = supplierPriorityQueue.iterator();
				(i.hasNext() || currentQuantity >= quantityNeeded);) {
			SpecificationEntity<PropertySpecification> propertyEntity = i.next();
			BuildingSpecification buildingSpec = (BuildingSpecification) propertyEntity.getSpecificationOfType(PropertySpecificationType.BUILDING);
			currentQuantity += buildingSpec.getProductForSale().get(product);
			
			supplierPropertyList.add(propertyEntity);
		}
		if (quantityNeeded > currentQuantity) { //didn't meet criteria for supplies
			supplierPropertyList = new ArrayList<SpecificationEntity<PropertySpecification>>();
		}
		
		return supplierPropertyList;
	}
	
	public boolean isRequestProductsSuccessful(HashMap<Product, List<SpecificationEntity<PropertySpecification>>> productPropertyLocationMap) {
		for (Iterator<Product> i = productPropertyLocationMap.keySet().iterator(); 
				i.hasNext();) {
			if (productPropertyLocationMap.get(i.next()).isEmpty()) {
				return false;
			}
		}
		
		return true;
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
		
		BuildingSpecification buildingSpec1 = (BuildingSpecification) p1.getSpecificationOfType(PropertySpecificationType.BUILDING);
		BuildingSpecification buildingSpec2 = (BuildingSpecification) p2.getSpecificationOfType(PropertySpecificationType.BUILDING);
		
		if (buildingSpec1 == null && buildingSpec2 != null) {
			return 1;
		} else if (buildingSpec1 != null && buildingSpec2 == null) {
			return -1;
		} else if (buildingSpec1 != null && buildingSpec2 != null) {
			Integer quantity1 = buildingSpec1.getProductForSale().get(product);
			Integer quantity2 = buildingSpec2.getProductForSale().get(product);
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