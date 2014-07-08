package specification.property.building;

import specification.desirability.PoliceSpecification;
import specification.property.zone.ZoneType;

public class PoliceStationSpecification extends BuildingSpecification {

	public PoliceStationSpecification() {
		super(BuildingType.POLICE_STATION, ZoneType.URBAN_SERVICE);
		getDesirabilitySpecificationEntity().addSpecification(new PoliceSpecification());
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Police station";
	}
	
	
}
