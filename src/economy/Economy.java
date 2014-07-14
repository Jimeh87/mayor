package economy;

import grid.Grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import objects.Person;
import specification.SpecificationEntity;
import specification.desirability.DesirabilitySpecification;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.building.BuildingSpecification;

public class Economy {

	public Economy(Grid<PropertySpecification> propertyGrid, Grid<DesirabilitySpecification> desirabilityGrid) {
		this.dGrid = desirabilityGrid;
		this.pGrid = propertyGrid;
		migrationHandler = new MigrationHandler();
		supplyLocationFinder = new SupplyLocationFinder(pGrid, supplyAndDemand.getDemand());
		supplyAndDemand = new SupplyAndDemand(pGrid);
		buildingHandler = new BuildingHandler(pGrid, supplyAndDemand);
	}
	
	private Grid<DesirabilitySpecification> dGrid;
	private Grid<PropertySpecification> pGrid;
	private SupplyLocationFinder supplyLocationFinder;
	private List<Person> citizenList = new ArrayList<Person>();
	private MigrationHandler migrationHandler;
	private SupplyAndDemand supplyAndDemand;
	private BuildingHandler buildingHandler;

	public Grid<DesirabilitySpecification> getDesirabilityGrid() {
		return dGrid;
	}

	public void tick() {
		for (Iterator<SpecificationEntity<PropertySpecification>> it = pGrid.iterator();
				it.hasNext();) {
			SpecificationEntity<PropertySpecification> propertyEntity = it.next();
			BuildingSpecification buildingSpec = (BuildingSpecification) propertyEntity.getSpecificationOfType(PropertySpecificationType.BUILDING);
			if (buildingSpec != null) {
				supplyLocationFinder.findProducts(propertyEntity, buildingSpec.getProductDemand());
			}
		}
		citizenList.add(migrationHandler.addPerson(pGrid, dGrid));
	}
}