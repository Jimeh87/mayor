package objects;

import specification.property.BuildingType;
import specification.property.ZoneType;

/**
 * Used to indicate the current cursor in use.
 * @author Josh
 */
public enum CursorType {
	ZONE_EMPTY       (ZoneType.EMPTY,       null,                        CursorIndicator.EMPTY),
	ZONE_RESIDENTIAL (ZoneType.RESIDENTIAL, null,                        CursorIndicator.RESIDENTIAL),
	ZONE_COMMERCIAL  (ZoneType.COMMERCIAL,  null,                        CursorIndicator.COMMERCIAL),
	ZONE_INDUSTRIAL  (ZoneType.INDUSTRIAL,  null,                        CursorIndicator.INDUSTRIAL),
	POLICE_STATION   (null,                 BuildingType.POLICE_STATION, CursorIndicator.POLICE_STATION),
	ZONE_BULLDOZE    (null,                 null,                        CursorIndicator.BULLDOZE);
	
	ZoneType zoneType;
	BuildingType buildingType;
	CursorIndicator cursorIndicator;
	CursorType(ZoneType zoneType, BuildingType buildingType, CursorIndicator cursorIndicator) {
		this.zoneType = zoneType;
		this.buildingType = buildingType;
		this.cursorIndicator = cursorIndicator;
	}
	
	public String getPaneId() {
		if (buildingType != null) {
			return buildingType.getPaneId();
		}
		return zoneType.getPaneId();
	}
	
	public CursorIndicator getCursorIndicator() {
		return cursorIndicator;
	}

	public ZoneType getZoneType() {
		return zoneType;
	}

	public BuildingType getBuildingType() {
		return buildingType;
	}
}  
