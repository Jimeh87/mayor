package specification.property;

import specification.desirability.PoliceSpecification;
import specification.property.zone.ZoneType;

public class FarmSpecification extends BuildingSpecification {

	public FarmSpecification() {
		super(BuildingType.FARM, ZoneType.URBAN_SERVICE);
		getDesirabilitySpecificationEntity().addSpecification(new PoliceSpecification());
	}
	

}
