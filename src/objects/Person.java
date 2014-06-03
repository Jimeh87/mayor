package objects;

public class Person {
	
	public Person(PersonType personType) {
		this.personType = personType;
	}
	
	PersonType personType;

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}
}
