package specification.property.building;

import grid.Grid;
import objects.Tile;

import org.junit.Before;
import org.junit.Test;

import specification.SpecificationEntity;
import specification.desirability.DesirabilitySpecification;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.TileSpecification;
import specification.property.building.concrete.PoliceStationSpecification;

public class BuildingSpecificationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSplash() {
		int xSize = 20;
		int ySize = 20;
		Grid<DesirabilitySpecification> dGrid = new Grid<DesirabilitySpecification>(xSize, ySize);
		Grid<PropertySpecification> pGrid = new Grid<PropertySpecification>(xSize, ySize);
		
		int xCurrent = 0;
		int yCurrent = 10;
		SpecificationEntity<PropertySpecification> property = pGrid.getSpecificationEntity(xCurrent, yCurrent);
		property.addSpecification(new TileSpecification(new Tile(xCurrent, yCurrent)));
		property.addSpecification(new PoliceStationSpecification());
		
		PoliceStationSpecification policeStationSpec = (PoliceStationSpecification) property.getSpecificationOfType(PropertySpecificationType.BUILDING);
		policeStationSpec.applySplash(dGrid, property);
	}

}
