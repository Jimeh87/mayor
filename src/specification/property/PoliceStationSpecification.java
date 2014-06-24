package specification.property;

import desirability.specification.PoliceSpecification;

public class PoliceStationSpecification extends BuildingSpecification {

	public PoliceStationSpecification() {
		super(BuildingType.POLICE_STATION, 0);
		getDesirabilitySpecificationEntity().addSpecification(new PoliceSpecification());
	}
	

}
