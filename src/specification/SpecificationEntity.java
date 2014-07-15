package specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationEntity<T extends Specification> {
	
	public SpecificationEntity() {
		
	}
	
	private final List<T> specificationList = new ArrayList<T>();
	
	public List<T> getSpecificationList() {
		return specificationList;
	}

	public void addSpecification(T specification) {
		getSpecificationList().add(specification);
	}
	
	public void removeSpecification(T specification) {
		boolean removeSuccessful = getSpecificationList().remove(specification);
		
		if (!removeSuccessful) {
			throw new IllegalStateException("Could not remove Specification");
		}
	}
	
	public List<T> getSpecificationListOfType(SpecificationType specificationType) {
		
		List<T> specificationList = new ArrayList<T>();
		for (T specification : getSpecificationList()) {
			
			if (specificationType.getSpecificationClass().isInstance(specification)) {
				specificationList.add(specification);
			}
			
		}
		
		return specificationList;
	}
	
	public <U extends T> T getSpecificationOfType(SpecificationType SpecificationType) {
		
		T matchingSpecification = null;
		for (T specification : getSpecificationList()) {
			
			if (SpecificationType.getSpecificationClass().isInstance(specification)) {
				if (matchingSpecification != null) {//we already found one. There are multiple which means this method should not be used.
					throw new IllegalStateException("There are multiple specifications of this type in this Property. Should use getSpecificationListOfType() instead.");
				}
				matchingSpecification = specification;
			}
			
		}
		
		return matchingSpecification;
	}
}
