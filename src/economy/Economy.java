package economy;

import grid.Grid;

import java.util.ArrayList;
import java.util.List;

import objects.MayorUtil;
import objects.Person;
import objects.PersonType;
import specification.desirability.DesirabilitySpecification;

public class Economy {
	
	Grid<DesirabilitySpecification> desirabilityGrid;
	public Economy() {
		for (int i = 0; i < 20; i++) {
			personMigrationPool.add(new Person(null));
		}
	}
	
	List<Person> personMigrationPool = new ArrayList<Person>();
	
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
