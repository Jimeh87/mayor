package property.specification;

import property.BuildingType;

public class BuildingSpecification extends PropertySpecification {
	public BuildingSpecification(BuildingType buildingType, Integer currentOccupancy) {
		setBuildingType(buildingType);
		setCurrentOccupancy(currentOccupancy);
	}
	private BuildingType buildingType;
	private Integer currentOccupancy;
	
	public BuildingType getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(BuildingType buildingType) {
		if (buildingType == null) {
			throw new IllegalArgumentException("BuildingType is required for BuildingSpecification");
		}
		this.buildingType = buildingType;
	}
	public Integer getCurrentOccupancy() {
		return currentOccupancy;
	}
	public void setCurrentOccupancy(Integer currentOccupancy) {
		if (currentOccupancy > getBuildingType().getMaxOccupancy()) {
			throw new IllegalStateException("The current occupancy can not excede the max occupancy");
		}
		this.currentOccupancy = currentOccupancy;
	}
	public void getMaxOccupancy() {
		getBuildingType().getMaxOccupancy();
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
