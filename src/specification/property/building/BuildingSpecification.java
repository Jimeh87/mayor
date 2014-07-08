package specification.property.building;

import economy.Product;
import grid.Grid;

import java.util.HashMap;
import java.util.List;

import objects.MayorUtil;
import objects.Person;
import objects.Tile;
import specification.SpecificationEntity;
import specification.desirability.DesirabilitySpecification;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.TileSpecification;
import specification.property.zone.ZoneType;

public abstract class BuildingSpecification extends PropertySpecification {
	public BuildingSpecification(BuildingType buildingType, ZoneType zoneType) {
		setBuildingType(buildingType);
		setZoneType(zoneType);
		desirabilitySpecificationEntity = new SpecificationEntity<DesirabilitySpecification>();
	}
	private BuildingType buildingType;
	private ZoneType zoneType;
	private Person person;
	private int currentOccupancy; //not used yet
	private SpecificationEntity<DesirabilitySpecification> desirabilitySpecificationEntity;
	protected  HashMap<Product, Integer> productForSaleMap = new HashMap<Product, Integer>();
	private HashMap<Product, Integer> productDemand = new HashMap<Product, Integer>();
	public BuildingType getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(BuildingType buildingType) {
		if (buildingType == null) {
			throw new IllegalArgumentException("BuildingType is required for BuildingSpecification");
		}
		this.buildingType = buildingType;
	}
	public ZoneType getZoneType() {
		return zoneType;
	}
	public void setZoneType(ZoneType zoneType) {
		this.zoneType = zoneType;
	}
	public int getCurrentOccupancy() {
		return currentOccupancy;
	}
	/*
	public void setCurrentOccupancy(int currentOccupancy) {
		if (currentOccupancy > getBuildingType().getMaxOccupancy()) {
			throw new IllegalStateException("The current occupancy can not excede the max occupancy");
		}
		this.currentOccupancy = currentOccupancy;
	}
	public int getMaxOccupancy() {
		return getBuildingType().getMaxOccupancy();
	}*/
	
	public SpecificationEntity<DesirabilitySpecification> getDesirabilitySpecificationEntity() {
		return desirabilitySpecificationEntity;
	}
	public void setDesirabilitySpecificationEntity(
			SpecificationEntity<DesirabilitySpecification> desirabilitySpecificationEntity) {
		this.desirabilitySpecificationEntity = desirabilitySpecificationEntity;
	}
	
	@Override
	public void applySplash(Grid<DesirabilitySpecification> dGrid, SpecificationEntity<PropertySpecification> parentEntity) {
		TileSpecification tileSpec = (TileSpecification) parentEntity.getSpecificationOfType(PropertySpecificationType.TILE);
		Tile tile = tileSpec.getTile();
		
		List<DesirabilitySpecification> desirabilitySpecList = desirabilitySpecificationEntity.getSpecificationList();
		for (DesirabilitySpecification desirabilitySpec : desirabilitySpecList) {

			int splashRadius = desirabilitySpec.getSplashRadius();
			for (int x = 0; x < splashRadius; x++) {
				//r^2 = x^2*y^2
				int yMax = (int) Math.ceil(MayorUtil.pythagorean((double) x, null, (double) splashRadius));
				for (int y = 0; y < yMax; y++) {
					//topRight +x, +y
					addSpecToEntityOnGrid(dGrid, tile.getXLocation() + x, tile.getYLocation() + y, desirabilitySpec);
					if (x != 0 || y != 0) {
						//bottomLeft -x, -y
						addSpecToEntityOnGrid(dGrid, tile.getXLocation() - x, tile.getYLocation() - y, desirabilitySpec);
					}
					if (x != 0 && y != 0) { //prevent duplicates on zero overlap
						//bottomRight +x, -y
						addSpecToEntityOnGrid(dGrid, tile.getXLocation() + x, tile.getYLocation() - y, desirabilitySpec);
						//topLeft -x, +y
						addSpecToEntityOnGrid(dGrid, tile.getXLocation() - x, tile.getYLocation() + y, desirabilitySpec);
					}
					
				}
			}
		}
	}

	/**
	 * Adds spec to Specification entity safely
	 * @param dGrid
	 * @param x
	 * @param y
	 */
	private void addSpecToEntityOnGrid(Grid<DesirabilitySpecification> dGrid, int x, int y, DesirabilitySpecification desirabilitySpec) {
		if (x < 0 || x >= dGrid.getXSize() || y < 0 || y >= dGrid.getYSize()) {
			return; //Prevents Specification from adding off Grid
		}

		dGrid.getSpecificationEntity(x, y).addSpecification(desirabilitySpec);

	}
	
	public HashMap<Product, Integer> getProductForSaleMap() {
		return productForSaleMap;
	}
	public void setProductForSaleMap(HashMap<Product, Integer> productForSaleMap) {
		this.productForSaleMap = productForSaleMap;
	}
	public HashMap<Product, Integer> getProductDemand() {
		return productDemand;
	}
	public void setProductDemand(HashMap<Product, Integer> productDemand) {
		this.productDemand = productDemand;
	}
	
	public void incrementProductDemand(Product product, Integer demand) {
		if (productDemand.containsKey(product)) {
			productDemand.put(product, productDemand.get(product) + demand);
		} else {
			productDemand.put(product, demand);
		}
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	@Override
	public String getPaneId() {
		return buildingType.getPaneId();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}
