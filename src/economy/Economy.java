package economy;

import java.util.ArrayList;
import java.util.List;

import objects.Grid;
import objects.MayorUtil;
import objects.Person;
import objects.PersonType;
import desirability.specification.DesirabilitySpecification;

public class Economy {
	
	Grid<DesirabilitySpecification> desirabilityGrid;
	public Economy() {
		
	}
	
	private List<Person> generatePersonPool() {
		
		List<Person> personPoolList = new ArrayList<Person>();
		for (int i = 0; i < 100; i++) {
			personPoolList.add(new Person(MayorUtil.randomEnum(PersonType.class)));
		}
		
		return personPoolList;
	}
	
	public void migrationHandler() {
		
		
	}

	public Grid<DesirabilitySpecification> getDesirabilityGrid() {
		return desirabilityGrid;
	}

	public void setDesirabilityGrid(Grid<DesirabilitySpecification> desirabilityGrid) {
		this.desirabilityGrid = desirabilityGrid;
	}
	
	//private addPersonToCity(person);
}
