package specification.property.zone;


public class ResidentialZoneSpecification extends ZoneSpecification {

	public ResidentialZoneSpecification() {
		super(ZoneType.RESIDENTIAL);
	}

	@Override
	public String getName() {
		return "Residential zone";
	}

}
