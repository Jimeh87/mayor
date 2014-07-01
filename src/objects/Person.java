package objects;

import specification.SpecificationEntity;
import specification.property.PropertySpecification;

public class Person {
	
	public Person() {
	}
	
	SpecificationEntity<PropertySpecification> homeEntity = null;

	public SpecificationEntity<PropertySpecification> getHomeEntity() {
		return homeEntity;
	}

	public void setHomeEntity(SpecificationEntity<PropertySpecification> homeEntity) {
		this.homeEntity = homeEntity;
	}
}
