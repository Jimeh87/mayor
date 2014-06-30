package specification.property;

import specification.desirability.PoliceSpecification;

public class FarmSpecification extends BuildingSpecification {

	public FarmSpecification() {
		super(BuildingType.FARM, ZoneType.URBAN_SERVICE);
		getDesirabilitySpecificationEntity().addSpecification(new PoliceSpecification());
	}
	

}
