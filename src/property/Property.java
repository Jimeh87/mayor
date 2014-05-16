package property;

import java.util.ArrayList;
import java.util.List;

import property.specification.BuildingSpecification;
import property.specification.PropertySpecification;
import property.specification.PropertySpecificationType;
import property.specification.TileSpecification;
import property.specification.ZoneSpecification;
import property.specification.ZoneType;

public class Property {
	
	public Property() {
	}
	
	private final List<PropertySpecification> specificationList = new ArrayList<PropertySpecification>();
	
	public List<PropertySpecification> getSpecificationList() {
		return specificationList;
	}

	public void addSpecification(PropertySpecification specification) {
		getSpecificationList().add(specification);
	}
	
	public void removeSpecification(PropertySpecification specification) {
		boolean removeSuccessful = getSpecificationList().remove(specification);
		
		if (!removeSuccessful) {
			throw new IllegalStateException("Could not remove Specification");
		}
	}
	
	public List<PropertySpecification> getPropertySpecificationListOfType(PropertySpecificationType propertySpecificationType) {
		
		List<PropertySpecification> specificationList = new ArrayList<PropertySpecification>();
		for (PropertySpecification specification : getSpecificationList()) {
			
			if (propertySpecificationType.getSpecificationClass().isInstance(specification)) {
				specificationList.add(specification);
			}
			
		}
		
		return specificationList;
	}
	
	public PropertySpecification getPropertySpecificationOfType(PropertySpecificationType propertySpecificationType) {
		
		PropertySpecification matchingSpecification = null;
		for (PropertySpecification specification : getSpecificationList()) {
			
			if (propertySpecificationType.getSpecificationClass().isInstance(specification)) {
				if (matchingSpecification != null) {//we already found one. There are multiple which means this method should not be used.
					throw new IllegalStateException("There are multiple specifications of this type in this Property. Should use getPropertySpecificationListOfType() instead.");
				}
				matchingSpecification = specification;
			}
			
		}
		
		return matchingSpecification;
	}
	
	public void tick() {
		TileSpecification tileSpec = (TileSpecification) getPropertySpecificationOfType(PropertySpecificationType.TILE);
		ZoneSpecification zoneSpec = (ZoneSpecification) getPropertySpecificationOfType(PropertySpecificationType.ZONE);
		BuildingSpecification buildingSpec = (BuildingSpecification) getPropertySpecificationOfType(PropertySpecificationType.BUILDING);
		
		if (zoneSpec != null && buildingSpec == null) {
			BuildingType buildingType;
			if (zoneSpec.getZoneType() == ZoneType.RESIDENTIAL) {
				buildingType = BuildingType.RES_SMALL;
			} else if (zoneSpec.getZoneType() == ZoneType.COMMERCIAL) {
				buildingType = BuildingType.COM_SMALL;
			} else if (zoneSpec.getZoneType() == ZoneType.INDUSTRIAL) {
				buildingType = BuildingType.IND_SMALL;
			} else {
				throw new IllegalStateException("Logic error.");
			}
			buildingSpec = new BuildingSpecification(buildingType, 0);
			addSpecification(buildingSpec);
		}
		
		if (buildingSpec != null) {
			tileSpec.getTile().setPaneId(buildingSpec.getBuildingType().getPaneId());
		} else if (zoneSpec != null) {
			tileSpec.getTile().setPaneId(zoneSpec.getZoneType().getPaneId());
		}
		tileSpec.getTile().refreshPane();
	}
}