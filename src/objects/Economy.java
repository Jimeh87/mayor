package objects;

import java.util.ArrayList;
import java.util.List;

import property.Property;
import property.specification.BuildingSpecification;
import property.specification.PropertySpecificationType;
import property.specification.ZoneSpecification;

public class Economy {
	
	public Economy() {
		
	}
	
	private static int MAX_PERSON_POOL_LIST_SIZE = 100;
	List<Person> residentList = new ArrayList<Person>();
	
	private void migrationHandler(Grid grid) {
		//getting counts... kinda ugly but it works for now.
		List<Property> emptyResidentialList = new ArrayList<Property>();
		List<Property> occupiedResidentialList = new ArrayList<Property>();
		List<Property> emptyCommercialList = new ArrayList<Property>();
		List<Property> occupiedCommercialList = new ArrayList<Property>();
		List<Property> emptyIndustrialList = new ArrayList<Property>();
		List<Property> occupiedIndustrialList = new ArrayList<Property>();
		
		int unoccupiedResidentialCount = 0;
		int unoccupiedCommercialCount = 0;
		int unoccupiedIndustrialCount = 0;
		int maxOccupancyResidentialCount = 0;
		int maxOccupancyCommercialCount = 0;
		int maxOccupancyIndustrialCount = 0;
		for (int x = 0; x < grid.getXSize(); x++) {
			for (int y = 0; y < grid.getYSize(); y++) {
				Property property = grid.getProperty(x, y);
				ZoneSpecification zoneSpec = (ZoneSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE);
				BuildingSpecification buildingSpec = (BuildingSpecification) property.getSpecificationOfType(PropertySpecificationType.BUILDING);
				
				if (zoneSpec != null) {
					if (buildingSpec == null) {
						switch (zoneSpec.getZoneType()) {
							case RESIDENTIAL:	emptyResidentialList.add(property);
												break;
							case COMMERCIAL: 	emptyCommercialList.add(property);
												break;
							case INDUSTRIAL:	emptyIndustrialList.add(property);
												break;
							default:			throw new IllegalStateException("unaccounted for ZoneType");
						}
					} else { //People can only use zoned tiles right now. Will change later
						switch (zoneSpec.getZoneType()) {
							case RESIDENTIAL:	occupiedResidentialList.add(property);
												unoccupiedResidentialCount += buildingSpec.getMaxOccupancy() + buildingSpec.getCurrentOccupancy();
												maxOccupancyResidentialCount += buildingSpec.getMaxOccupancy();
												break;
							case COMMERCIAL: 	occupiedCommercialList.add(property);
												unoccupiedCommercialCount += buildingSpec.getMaxOccupancy() + buildingSpec.getCurrentOccupancy();
												maxOccupancyCommercialCount += buildingSpec.getMaxOccupancy();
												break;
							case INDUSTRIAL:	occupiedIndustrialList.add(property);
												unoccupiedIndustrialCount += buildingSpec.getMaxOccupancy() + buildingSpec.getCurrentOccupancy();
												maxOccupancyIndustrialCount += buildingSpec.getMaxOccupancy();
												break;
							default:			throw new IllegalStateException("unaccounted for ZoneType");
							
							
						}
					}
				}
			}
		}
		
		List<Person> personPoolList = generatePersonPool();
		for (Person person : personPoolList) {
			PersonType personType = person.getPersonType();
			
			//this needs to become more generic. Will change later.
			int demandForCity = 0;
			if (unoccupiedResidentialCount > 0) {
				demandForCity += personType.getUnoccupiedResDemand();
			} else if (emptyResidentialList.size() > 0) {
				demandForCity += personType.getEmptyResDemand();
			}
			
			if (unoccupiedCommercialCount > 0 || unoccupiedIndustrialCount > 0) {
				demandForCity += personType.getUnoccupiedJobsDemand();
			}
			
			if (emptyIndustrialList.size() > 0 || emptyCommercialList.size() > 0) {
				demandForCity += (personType.getEmptyBusDemand() + personType.getEmptyIndDemand()) / 2;
			}
			
			if (demandForCity > 10) { //demand is high enough
	//			addPersonToCity(person, property, );
			}
			
		}
		
		
	}
	
	private List<Person> generatePersonPool() {
		
		List<Person> personPoolList = new ArrayList<Person>();
		for (int i = 0; i < MAX_PERSON_POOL_LIST_SIZE; i++) {
			personPoolList.add(new Person(MayorUtil.randomEnum(PersonType.class)));
		}
		
		return personPoolList;
	}
	
	//private addPersonToCity(person);
}
