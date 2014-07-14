package economy;

import grid.Grid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import objects.Person;
import objects.Tile;
import specification.SpecificationEntity;
import specification.desirability.DesirabilitySpecification;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.TileSpecification;
import specification.property.building.BuildingSpecification;
import specification.property.building.concrete.BasicHomeSpecification;
import specification.property.zone.ZoneSpecification;
import specification.property.zone.ZoneType;

public class MigrationHandler {
	
	public MigrationHandler() {
		for (int i = 0; i < 20; i++) {
			personMigrationPool.add(new Person());
		}
	}
	
	List<Person> personMigrationPool = new ArrayList<Person>();
	
	public Person addPerson(Grid<PropertySpecification> pGrid, Grid<DesirabilitySpecification> dGrid) {
		if (personMigrationPool.isEmpty()) { //logic to add people to pool does not exist yet
			return null;
		}
		PriorityQueue<SpecificationEntity<PropertySpecification>> resZonePriorityQueue = new PriorityQueue<SpecificationEntity<PropertySpecification>>(new ResidentialPriorityComparator(dGrid));
		Iterator<SpecificationEntity<PropertySpecification>> i = pGrid.iterator();
		while (i.hasNext()) {
			SpecificationEntity<PropertySpecification> propertySpecEntity = i.next();
			ZoneSpecification zoneSpec = (ZoneSpecification) propertySpecEntity.getSpecificationOfType(PropertySpecificationType.ZONE);
			if (zoneSpec != null && zoneSpec.getZoneType() == ZoneType.RESIDENTIAL) {
				resZonePriorityQueue.add(propertySpecEntity);
			}
		}
		
		Person newCitizen = null;
		//since only one person per building... person can't move in if all locations are occupied
		SpecificationEntity<PropertySpecification> bestPropertyEntity = resZonePriorityQueue.peek();
		if (bestPropertyEntity != null
				&& ((BuildingSpecification) bestPropertyEntity.getSpecificationOfType(PropertySpecificationType.BUILDING)) == null) {
			newCitizen = personMigrationPool.get(personMigrationPool.size() - 1);
			addPersonAsCitizen(newCitizen, bestPropertyEntity);
		}
		
		return newCitizen;
	}

	/**
	 * TODO: Need to rethink how this will work. Not a big fan of adding person to house and house to person.
	 * @param person
	 * @param propertyEntity
	 */
	private void addPersonAsCitizen(Person person, SpecificationEntity<PropertySpecification> propertyEntity) {
		personMigrationPool.remove(person);
		person.setHomeEntity(propertyEntity);
		BasicHomeSpecification homeSpec = new BasicHomeSpecification();
		homeSpec.setPerson(person);
		propertyEntity.addSpecification(homeSpec);
		Tile tile = ((TileSpecification) propertyEntity.getSpecificationOfType(PropertySpecificationType.TILE)).getTile();
		tile.setPaneId(homeSpec.getPaneId());
		tile.refreshPane();
	}
	
	/**
	 * Old code, will need in future
	 * @return
	 */
	/*
	private List<Person> generatePersonPool() {
		
		List<Person> personPoolList = new ArrayList<Person>();
		for (int i = 0; i < 100; i++) {
			personPoolList.add(new Person(MayorUtil.randomEnum(PersonType.class)));
		}
		
		return personPoolList;
	}*/
}

class ResidentialPriorityComparator implements Comparator<SpecificationEntity<PropertySpecification>> {

	ResidentialPriorityComparator(Grid<DesirabilitySpecification> dGrid) {
		this.dGrid = dGrid;
	}
	
	private Grid<DesirabilitySpecification> dGrid;
	
	@Override
	public int compare(SpecificationEntity<PropertySpecification> p1, SpecificationEntity<PropertySpecification> p2) {
		
		Tile tile1 = ((TileSpecification) p1.getSpecificationOfType(PropertySpecificationType.TILE)).getTile();
		Tile tile2 = ((TileSpecification) p2.getSpecificationOfType(PropertySpecificationType.TILE)).getTile();
		
		//will be used when buildings can hold more than one person
		BuildingSpecification buildingSpec1 = (BuildingSpecification) p1.getSpecificationOfType(PropertySpecificationType.BUILDING);
		BuildingSpecification buildingSpec2 = (BuildingSpecification) p2.getSpecificationOfType(PropertySpecificationType.BUILDING);
		
		if (buildingSpec1 == null && buildingSpec2 == null) {
			SpecificationEntity<DesirabilitySpecification> desireabilitySpecEntity1 = dGrid.getSpecificationEntity(tile1.getXLocation(), tile1.getYLocation());
			SpecificationEntity<DesirabilitySpecification> desireabilitySpecEntity2 = dGrid.getSpecificationEntity(tile2.getXLocation(), tile2.getYLocation());
			return desireabilitySpecEntity2.getSpecificationList().size() - desireabilitySpecEntity1.getSpecificationList().size(); //TODO, this is wrong. I should be comparing desirability. Will figure out later
		} else if (buildingSpec1 != null && buildingSpec2 == null) {
			return 1;
		} else if (buildingSpec1 == null && buildingSpec2 != null) {
			return -1;
		}
		
		return 0;
	}
}
