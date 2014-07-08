package specification.property.zone;


public class AgriculturalZoneSpecification extends ZoneSpecification {

	public AgriculturalZoneSpecification() {
		super(ZoneType.AGRICULTURAL);
	}

	@Override
	public String getName() {
		return "Agricultural zone";
	}

}
