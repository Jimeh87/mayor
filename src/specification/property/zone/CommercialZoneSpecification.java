package specification.property.zone;


public class CommercialZoneSpecification extends ZoneSpecification {

	public CommercialZoneSpecification() {
		super(ZoneType.COMMERCIAL);
	}

	@Override
	public String getName() {
		return "Commercial zone";
	}

}
