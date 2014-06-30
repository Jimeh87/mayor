package specification.property;

import grid.Grid;
import specification.SpecificationEntity;
import specification.desirability.DesirabilitySpecification;



public abstract class ZoneSpecification extends PropertySpecification {
	public ZoneSpecification(ZoneType zoneType) {
		setZoneType(zoneType);
	}
	
	private ZoneType zoneType;
	
	public ZoneType getZoneType() {
		return zoneType;
	}
	
	public void setZoneType(ZoneType zoneType) {
		if (zoneType == null) {
			throw new IllegalArgumentException("ZoneType is required for ZoneSpecification");
		} else if (zoneType == ZoneType.EMPTY) {
			throw new IllegalArgumentException("ZoneType.EMPTY invalid as a ZoneSpecification");
		}
		this.zoneType = zoneType;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPaneId() {
		return zoneType.getPaneId();
	}

	@Override
	public void applySplash(Grid<DesirabilitySpecification> dGrid, SpecificationEntity<PropertySpecification> parentEntity) {
		// TODO Auto-generated method stub
		
	}
}
