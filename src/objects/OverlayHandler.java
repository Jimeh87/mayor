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
	private static final double MAX_OPACITY = .19;
	private Grid<PropertySpecification> pGrid;
	private boolean addOverlay = true;
	
	public void displayOverlay(Grid<?> grid, SpecificationType specificationType, OverlayColor overlayColor) {
		
		GridIterator<?> gridIterator = grid.iterator();
		while (gridIterator.hasNext()) {
			SpecificationEntity<?> entity = gridIterator.next();
			
			List<?> specList = entity.getSpecificationListOfType(specificationType);
			if (!specList.isEmpty()) {
				TileSpecification tileSpec = (TileSpecification) pGrid.getSpecificationEntity(gridIterator.getX(), gridIterator.getY()).getSpecificationOfType(PropertySpecificationType.TILE);
				System.out.println(tileSpec.getTile().getXLocation() + ", " + tileSpec.getTile().getYLocation());
				if (addOverlay) {
					tileSpec.getTile().getOverlayPane().setId(overlayColor.getPaneId());
					tileSpec.getTile().getPane().setOpacity(getOpacity(specList.size()));
				} else {
					tileSpec.getTile().getOverlayPane().setId(null);
					tileSpec.getTile().getPane().setOpacity(1);
					tileSpec.getTile().refreshPane();
				}
			}

		}
		
		addOverlay = !addOverlay;
	}

	private double getOpacity(int intensitySize) {
		double intensity = 1 - (.3*Math.exp(intensitySize*.2));
		if (intensity < MAX_OPACITY) {
			intensity = MAX_OPACITY;
		}
		System.out.println("i:"+intensity);
		return intensity;
	}

}
