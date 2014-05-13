package property.specification;


public class ZoneSpecification extends PropertySpecification {
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
}
