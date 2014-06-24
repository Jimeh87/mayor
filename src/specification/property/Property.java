package specification.property;

import specification.SpecificationEntity;

public class Property extends SpecificationEntity<PropertySpecification> {
	
	public Property() {
	}
		
	public void tick() {
		TileSpecification tileSpec = (TileSpecification) getSpecificationOfType(PropertySpecificationType.TILE);
		ZoneSpecification zoneSpec = (ZoneSpecification) getSpecificationOfType(PropertySpecificationType.ZONE);
		BuildingSpecification buildingSpec = (BuildingSpecification) getSpecificationOfType(PropertySpecificationType.BUILDING);
		
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
			//buildingSpec = new BuildingSpecification(buildingType, 0);
			//addSpecification(buildingSpec);
		}
		
		if (buildingSpec != null) {
			tileSpec.getTile().setPaneId(buildingSpec.getBuildingType().getPaneId());
		} else if (zoneSpec != null) {
			tileSpec.getTile().setPaneId(zoneSpec.getZoneType().getPaneId());
		}
		tileSpec.getTile().refreshPane();
	}
}