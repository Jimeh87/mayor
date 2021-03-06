package objects;

import grid.Grid;
import grid.GridIterator;

import java.util.Iterator;
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
	private OverlayColor activeOverlayColor = null;
	
	public void displayOverlay(Grid<?> grid, SpecificationType specificationType, OverlayColor overlayColor) {
		
		GridIterator<?> gridIterator = grid.iterator();
		while (gridIterator.hasNext()) {
			SpecificationEntity<?> entity = gridIterator.next();
			TileSpecification tileSpec = (TileSpecification) pGrid.getSpecificationEntity(gridIterator.getX(), gridIterator.getY()).getSpecificationOfType(PropertySpecificationType.TILE);
			clearTile(tileSpec.getTile());
			List<?> specList = entity.getSpecificationListOfType(specificationType);
			if (!specList.isEmpty()) {
				System.out.println(tileSpec.getTile().getXLocation() + ", " + tileSpec.getTile().getYLocation());
				if (activeOverlayColor != overlayColor) {
					tileSpec.getTile().getOverlayPane().setId(overlayColor.getPaneId());
					tileSpec.getTile().getPane().setOpacity(getOpacity(specList.size()));
				} 
			}
		}
		if (activeOverlayColor == overlayColor) {
			activeOverlayColor = null;
		} else if (activeOverlayColor != overlayColor) {
			activeOverlayColor = overlayColor;
		}
		
	}
	
	
	private double getOpacity(int intensitySize) {
		double intensity = 1 - (.3*Math.exp(intensitySize*.2));
		if (intensity < MAX_OPACITY) {
			intensity = MAX_OPACITY;
		}
		return intensity;
	}


	public void clearOverlay() {
		Iterator<SpecificationEntity<PropertySpecification>> gridIterator = pGrid.iterator();
		while (gridIterator.hasNext()) {
			TileSpecification tileSpec = (TileSpecification) gridIterator.next().getSpecificationOfType(PropertySpecificationType.TILE);
			clearTile(tileSpec.getTile());
		}
		activeOverlayColor = null;
	}
	
	private void clearTile(Tile tile) {
		tile.getOverlayPane().setId(null);
		tile.getPane().setOpacity(1);
		tile.refreshPane();
	}
	
	

}
