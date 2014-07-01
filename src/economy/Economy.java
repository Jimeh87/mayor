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
import specification.property.building.BasicHomeSpecification;
import specification.property.building.BuildingSpecification;
import specification.property.zone.ZoneSpecification;
import specification.property.zone.ZoneType;

public class Economy {
	
	Grid<DesirabilitySpecification> dGrid;
	public Economy(Grid<DesirabilitySpecification> desirabilityGrid) {
		this.dGrid = desirabilityGrid;
		for (int i = 0; i < 20; i++) {
			personMigrationPool.add(new Person());
		}
	}
	
	List<Person> personMigrationPool = new ArrayList<Person>();
	List<Person> citizenList = new ArrayList<Person>();
	
	public void migrationHandler(Grid<PropertySpecification> pGrid) {
		if (personMigrationPool.isEmpty()) { //logic to add people to pool does not exist yet
			return;
		}
		PriorityQueue<SpecificationEntity<PropertySpecification>> resZonePriorityQueue = new PriorityQueue<SpecificationEntity<PropertySpecification>>(new ResidentialPriorityComparator(dGrid));
		Iterator<SpecificationEntity<PropertySpecification>> i = pGrid.iterator();
		while (i.hasNext()) {
			SpecificationEntity<PropertySpecification> propertySpecEntity = i.next();
			ZoneSpecification zoneSpec = (ZoneSpecification) propertySpecEntity.getSpecificationOfType(PropertySpecificationType.ZONE);
			if (zoneSpec != null && zoneSpec.getZoneType() == ZoneType.RESIDENTIAL) {
				resZonePriorityQueue.offer(propertySpecEntity);
			}
		}
		
		//since only one person per building... person can't move in if all locations are occupied
		SpecificationEntity<PropertySpecification> bestPropertyEntity = resZonePriorityQueue.peek();
		if (bestPropertyEntity != null
				&& ((BuildingSpecification) bestPropertyEntity.getSpecificationOfType(PropertySpecificationType.BUILDING)) == null) {
			addPersonAsCitizen(personMigrationPool.get(personMigrationPool.size() - 1), bestPropertyEntity);
		}
	}

	private void addPersonAsCitizen(Person person, SpecificationEntity<PropertySpecification> propertyEntity) {
		personMigrationPool.remove(person);
		citizenList.add(person);
		person.setHomeEntity(propertyEntity);
		BasicHomeSpecification homeSpec = new BasicHomeSpecification();
		homeSpec.setPerson(person);
		propertyEntity.addSpecification(homeSpec);
		Tile tile = ((TileSpecification) propertyEntity.getSpecificationOfType(PropertySpecificationType.TILE)).getTile();
		tile.setPaneId(homeSpec.getPaneId());
		tile.refreshPane();
	}

	public Grid<DesirabilitySpecification> getDesirabilityGrid() {
		return dGrid;
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
	public int compare(SpecificationEntity<PropertySpecification> specEntityA, SpecificationEntity<PropertySpecification> specEntityB) {
		
		Tile tileA = ((TileSpecification) specEntityA.getSpecificationOfType(PropertySpecificationType.TILE)).getTile();
		Tile tileB = ((TileSpecification) specEntityB.getSpecificationOfType(PropertySpecificationType.TILE)).getTile();
		
		//will be used when buildings can hold more than one person
		BuildingSpecification buildingSpecA = (BuildingSpecification) specEntityA.getSpecificationOfType(PropertySpecificationType.BUILDING);
		BuildingSpecification buildingSpecB = (BuildingSpecification) specEntityB.getSpecificationOfType(PropertySpecificationType.BUILDING);
		
		if (buildingSpecA == null && buildingSpecB == null) {
			SpecificationEntity<DesirabilitySpecification> desireabilitySpecEntityA = dGrid.getSpecificationEntity(tileA.getXLocation(), tileA.getYLocation());
			SpecificationEntity<DesirabilitySpecification> desireabilitySpecEntityB = dGrid.getSpecificationEntity(tileB.getXLocation(), tileB.getYLocation());
			return desireabilitySpecEntityB.getSpecificationList().size() - desireabilitySpecEntityA.getSpecificationList().size(); //TODO, this is wrong. I should be comparing desirability. Will figure out later
		} else if (buildingSpecA == null && buildingSpecB != null) {
			return -1;
		} else if (buildingSpecA != null && buildingSpecB == null) {
			return 1;
		}
		
		return 0;
	}
}