package economy;

import java.util.ArrayList;
import java.util.List;

import objects.MayorUtil;
import objects.Person;
import objects.PersonType;
import property.Property;
import property.PropertySpecificationType;
import property.specification.BuildingSpecification;
import property.specification.ZoneSpecification;

public class Economy {
	
	public Economy() {
		
	}
	
	private List<Person> generatePersonPool() {
		
		List<Person> personPoolList = new ArrayList<Person>();
		for (int i = 0; i < 100; i++) {
			personPoolList.add(new Person(MayorUtil.randomEnum(PersonType.class)));
		}
		
		return personPoolList;
	}
	
	public void migrationHandler2() {
		
		
	}
	
	//private addPersonToCity(person);
}
