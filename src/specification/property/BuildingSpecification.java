package specification.property;

import java.util.List;

import objects.Grid;
import objects.Tile;
import specification.SpecificationEntity;
import desirability.specification.DesirabilitySpecification;

public abstract class BuildingSpecification extends PropertySpecification {
	public BuildingSpecification(BuildingType buildingType, int currentOccupancy) {
		setBuildingType(buildingType);
		setCurrentOccupancy(currentOccupancy);
		desirabilitySpecificationEntity = new SpecificationEntity<DesirabilitySpecification>();
	}
	private BuildingType buildingType;
	private int currentOccupancy;
	private SpecificationEntity<DesirabilitySpecification> desirabilitySpecificationEntity;
	
	public BuildingType getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(BuildingType buildingType) {
		if (buildingType == null) {
			throw new IllegalArgumentException("BuildingType is required for BuildingSpecification");
		}
		this.buildingType = buildingType;
	}
	public int getCurrentOccupancy() {
		return currentOccupancy;
	}
	public void setCurrentOccupancy(int currentOccupancy) {
		if (currentOccupancy > getBuildingType().getMaxOccupancy()) {
			throw new IllegalStateException("The current occupancy can not excede the max occupancy");
		}
		this.currentOccupancy = currentOccupancy;
	}
	public int getMaxOccupancy() {
		return getBuildingType().getMaxOccupancy();
	}
	
	public SpecificationEntity<DesirabilitySpecification> getDesirabilitySpecificationEntity() {
		return desirabilitySpecificationEntity;
	}
	public void setDesirabilitySpecificationEntity(
			SpecificationEntity<DesirabilitySpecification> desirabilitySpecificationEntity) {
		this.desirabilitySpecificationEntity = desirabilitySpecificationEntity;
	}
	
	public void applySplash(Grid<DesirabilitySpecification> dGrid, SpecificationEntity<PropertySpecification> parentEntity) {
		TileSpecification tileSpec = (TileSpecification) parentEntity.getSpecificationOfType(PropertySpecificationType.TILE);
		Tile tile = tileSpec.getTile();
		
		List<DesirabilitySpecification> desirabilitySpecList = desirabilitySpecificationEntity.getSpecificationList();
		for (DesirabilitySpecification desirabilitySpec : desirabilitySpecList) {

			int splashRadius = desirabilitySpec.getSplashRadius();
			for (int x = 0; x < splashRadius; x++) {
				//r^2 = x^2*y^2
				int yMax = (int) Math.ceil(Math.sqrt((double) ( (splashRadius * splashRadius) - (x * x) )) );
				for (int y = 0; y < yMax; y++) {
					
					if (x != 0 || y != 0) {
						//topRight +x, +y
						addSpecToEntityOnGrid(dGrid, tile.getXLocation() + x, tile.getYLocation() + y, desirabilitySpec);
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
	
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
