package specification.property;

import specification.desirability.PoliceSpecification;
import specification.property.zone.ZoneType;

public class PoliceStationSpecification extends BuildingSpecification {

	public PoliceStationSpecification() {
		super(BuildingType.POLICE_STATION, ZoneType.URBAN_SERVICE);
		getDesirabilitySpecificationEntity().addSpecification(new PoliceSpecification());
	}
	

}
