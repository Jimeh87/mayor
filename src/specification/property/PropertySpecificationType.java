package specification.property;

import specification.SpecificationType;
import specification.property.building.BuildingSpecification;
import specification.property.building.GeneratedBuildingSpecification;
import specification.property.building.SupplyAndDemandBuilding;
import specification.property.zone.AgriculturalZoneSpecification;
import specification.property.zone.CommercialZoneSpecification;
import specification.property.zone.IndustrialZoneSpecification;
import specification.property.zone.ResidentialZoneSpecification;
import specification.property.zone.ZoneSpecification;


public enum PropertySpecificationType implements SpecificationType {
	TILE(TileSpecification.class),
	ZONE(ZoneSpecification.class),
	BUILDING(BuildingSpecification.class),
	GENERATED_BUILDING(GeneratedBuildingSpecification.class),
	SUPPLY_AND_DEMAND_BUILDING(SupplyAndDemandBuilding.class),
	RES_ZONE(ResidentialZoneSpecification.class),
	COM_ZONE(CommercialZoneSpecification.class), 
	IND_ZONE(IndustrialZoneSpecification.class), 
	FARM_ZONE(AgriculturalZoneSpecification.class);
	
	private PropertySpecificationType(Class<?> specificationClass) {
		setSpecificationClass(specificationClass);
	}
	
	Class<?> specificationClass;
	public Class<?> getSpecificationClass() {
		return specificationClass;
	}
	public void setSpecificationClass(Class<?> specificationClass) {
		this.specificationClass = specificationClass;
	}
}