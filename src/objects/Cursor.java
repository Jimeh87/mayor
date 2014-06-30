package objects;

import specification.property.PropertySpecification;



public class Cursor{
	
	private PropertySpecification propertySpecification;
	
	//Construct
	public Cursor() {
	}

	public void setPropertySpecification(PropertySpecification defaultSpecification) {
		this.propertySpecification = defaultSpecification;
	}
	
	public PropertySpecification getPropertySpecification() {
		return propertySpecification;
	}


	
}
