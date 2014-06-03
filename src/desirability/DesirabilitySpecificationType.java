package desirability;

import objects.SpecificationType;
import desirability.specification.PowerSpecification;

public enum DesirabilitySpecificationType implements SpecificationType {
	TILE(PowerSpecification.class);
	
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
