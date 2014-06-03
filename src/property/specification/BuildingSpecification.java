package property.specification;

import property.BuildingType;
import property.PropertySpecification;

public class BuildingSpecification extends PropertySpecification {
	public BuildingSpecification(BuildingType buildingType, int currentOccupancy) {
		setBuildingType(buildingType);
		setCurrentOccupancy(currentOccupancy);
	}
	private BuildingType buildingType;
	private int currentOccupancy;
	
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
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
