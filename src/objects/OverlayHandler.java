package objects;

import grid.Grid;
import grid.GridIterator;

import java.util.List;

import specification.SpecificationEntity;
import specification.SpecificationType;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.TileSpecification;

public class OverlayHandler {

	public OverlayHandler(Grid<PropertySpecification> propertyGrid) {
		this.pGrid = propertyGrid;
	}
	
	private Grid<PropertySpecification> pGrid;
	private boolean addOverlay = true;
	
	public void displayOverlay(Grid<?> grid, SpecificationType specificationType) {
		
		GridIterator<?> gridIterator = grid.iterator();
		while (gridIterator.hasNext()) {
			SpecificationEntity<?> entity = gridIterator.next();
			
			List<?> specList = entity.getSpecificationListOfType(specificationType);
			if (!specList.isEmpty()) {
				TileSpecification tileSpec = (TileSpecification) pGrid.getSpecificationEntity(gridIterator.getX(), gridIterator.getY()).getSpecificationOfType(PropertySpecificationType.TILE);
				if (addOverlay) {
					tileSpec.getTile().getOverlayPane().setId("POverlay");
					tileSpec.getTile().getPane().setOpacity(.6);
					//tileSpec.getTile().lock();
				} else {
					//tileSpec.getTile().unLock();
					tileSpec.getTile().getOverlayPane().setId(null);
					tileSpec.getTile().getPane().setOpacity(1);
					tileSpec.getTile().refreshPane();
				}
			}

		}
		
		addOverlay = !addOverlay;
	}

}
