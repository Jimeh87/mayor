package property.specification;

import desirability.specification.PoliceSpecification;
import property.BuildingType;

public class PoliceStationSpecification extends BuildingSpecification {

	public PoliceStationSpecification() {
		super(BuildingType.POLICE_STATION, 0);
		getDesirabilitySpecificationEntity().addSpecification(new PoliceSpecification());
	}
	

}
