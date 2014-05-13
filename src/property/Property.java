package property;

import java.util.ArrayList;
import java.util.List;

import property.specification.PropertySpecification;
import property.specification.PropertySpecificationType;

public class Property {
	
	public Property() {
	}
	
	private final List<PropertySpecification> specificationList = new ArrayList<PropertySpecification>();
	
	public List<PropertySpecification> getSpecificationList() {
		return specificationList;
	}

	public void addSpecification(PropertySpecification specification) {
		getSpecificationList().add(specification);
	}
	
	public void removeSpecification(PropertySpecification specificationList) {
		
	}
	
	public List<PropertySpecification> getPropertySpecificationListOfType(PropertySpecificationType propertySpecificationType) {
		
		List<PropertySpecification> specificationList = new ArrayList<PropertySpecification>();
		for (PropertySpecification specification : getSpecificationList()) {
			
			if (propertySpecificationType.getSpecificationClass().isInstance(specification)) {
				specificationList.add(specification);
			}
			
		}
		
		return specificationList;
	}
	
	public PropertySpecification getPropertySpecificationOfType(PropertySpecificationType propertySpecificationType) {
		
		PropertySpecification matchingSpecification = null;
		for (PropertySpecification specification : getSpecificationList()) {
			
			if (propertySpecificationType.getSpecificationClass().isInstance(specification)) {
				if (matchingSpecification != null) {//we already found one. There are multiple which means this method should not be used.
					throw new IllegalStateException("There are multiple specifications of this type in this Property. Should use getPropertySpecificationListOfType() instead.");
				}
				matchingSpecification = specification;
			}
			
		}
		
		return matchingSpecification;
	}
}