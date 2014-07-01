package objects;

import grid.Grid;
import grid.GridIterator;

import java.util.List;

import specification.SpecificationEntity;
import specification.desirability.DesirabilitySpecification;
import specification.desirability.DesirabilitySpecificationType;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.TileSpecification;

public class OverlayHandler {

	public OverlayHandler(Grid<PropertySpecification> propertyGrid, Grid<DesirabilitySpecification> desirabilityGrid) {
		this.pGrid = propertyGrid;
		this.dGrid = desirabilityGrid;
	}
	
	private Grid<PropertySpecification> pGrid;
	private Grid<DesirabilitySpecification> dGrid;
	private boolean addOverlay = true;
	
	public void displayOverlay(DesirabilitySpecificationType desirabilitySpecificationType) {
		
		GridIterator<DesirabilitySpecification> gIterator = dGrid.iterator();
		while (gIterator.hasNext()) {
			SpecificationEntity<DesirabilitySpecification> dEntity = gIterator.next();
			
			List<DesirabilitySpecification> policeSpecList = dEntity.getSpecificationListOfType(desirabilitySpecificationType);
			if (!policeSpecList.isEmpty()) {
				TileSpecification tileSpec = (TileSpecification) pGrid.getSpecificationEntity(gIterator.getX(), gIterator.getY()).getSpecificationOfType(PropertySpecificationType.TILE);
				if (addOverlay) {
					tileSpec.getTile().getPane().setId("TODO"); //set overlay
				} else {
					tileSpec.getTile().refreshPane();
				}
			}

		}
		
		addOverlay = !addOverlay;
	}

}
