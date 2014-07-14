package specification.property.building;
import specification.SpecificationType;
import specification.property.building.concrete.BasicHomeSpecification;
import specification.property.building.concrete.FarmSpecification;

public enum BuildingSpecificationType implements SpecificationType {
	BASIC_HOME(BasicHomeSpecification.class),
	FARM(FarmSpecification.class);
	
	private BuildingSpecificationType(Class<?> specificationClass) {
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