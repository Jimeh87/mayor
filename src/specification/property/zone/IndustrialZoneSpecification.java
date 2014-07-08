package specification.property.zone;


public class IndustrialZoneSpecification extends ZoneSpecification {

	public IndustrialZoneSpecification() {
		super(ZoneType.INDUSTRIAL);
	}

	@Override
	public String getName() {
		return "Industrial zone";
	}
	
	
}
