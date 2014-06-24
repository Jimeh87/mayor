package specification.property;

import specification.desirability.PoliceSpecification;

public class PoliceStationSpecification extends BuildingSpecification {

	public PoliceStationSpecification() {
		super(BuildingType.POLICE_STATION, 0);
		getDesirabilitySpecificationEntity().addSpecification(new PoliceSpecification());
	}
	

}
