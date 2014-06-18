package desirability.specification;

import objects.SpecificationType;

public enum DesirabilitySpecificationType implements SpecificationType {
	POWER(PowerSpecification.class),
	POLICE(PoliceSpecification.class);
	
	private DesirabilitySpecificationType(Class<?> specificationClass) {
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
