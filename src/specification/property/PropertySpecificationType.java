package specification.property;

import specification.SpecificationType;
import specification.property.building.BuildingSpecification;
import specification.property.zone.CommercialZoneSpecification;
import specification.property.zone.ResidentialZoneSpecification;
import specification.property.zone.ZoneSpecification;


public enum PropertySpecificationType implements SpecificationType {
	TILE(TileSpecification.class),
	ZONE(ZoneSpecification.class),
	BUILDING(BuildingSpecification.class),
	RES_ZONE(ResidentialZoneSpecification.class),
	COM_ZONE(CommercialZoneSpecification.class);
	
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