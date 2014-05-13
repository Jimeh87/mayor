package property.specification;


public enum PropertySpecificationType {
	TILE(TileSpecification.class),
	ZONE(ZoneSpecification.class),
	BUILDING(BuildingSpecification.class);
	
	private PropertySpecificationType(Class<?> specificationClass) {
		setSpecificationClass(specificationClass);
	}
	
	Class<?> specificationClass;
	public Class<?> getSpecificationClass() {
		return specificationClass;
	}
	public void setSpecificationClass(Class<?> specificationClass) {
		this.specificationClass = specificationClass;
	}
}
