package objects;

import grid.Grid;
import specification.SpecificationType;
import specification.desirability.DesirabilitySpecification;
import specification.desirability.DesirabilitySpecificationType;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class OverlayMenuBuilder {
	private static final double BUTTON_MAX_WIDTH = 200;
	private static final double BUTTON_MAX_HEIGHT = 50;
	private Grid<DesirabilitySpecification> dGrid;
	private Grid<PropertySpecification> pGrid;
	private Pane pnTopRightGap;
	private Cursor cursor;
	private OverlayHandler overlayHandler;
	
	//Constructor
	public OverlayMenuBuilder(Grid<PropertySpecification> pGrid, Grid<DesirabilitySpecification> dGrid, 
			Pane pnTopRightGap, Cursor cursor, OverlayHandler overlayHandler) {
		this.pGrid = pGrid;
		this.dGrid = dGrid;
		this.pnTopRightGap = pnTopRightGap;
		this.cursor = cursor;
		this.overlayHandler = overlayHandler;
	}
	//Parent Node Generation and add children
	Parent generateGridPane() {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(1);gridPane.setVgap(1);
		gridPane.setMinSize(BUTTON_MAX_WIDTH, BUTTON_MAX_HEIGHT);
 
		gridPane.add(createButton("Police Overlay", dGrid, DesirabilitySpecificationType.POLICE, OverlayColor.POLICE),0,0);
		gridPane.add(createButton("Residential Overlay", pGrid, PropertySpecificationType.RES_ZONE, OverlayColor.RESIDENTIAL), 0, 1);
		gridPane.add(createButton("Commmercial Overlay", pGrid, PropertySpecificationType.COM_ZONE, OverlayColor.COMMERCIAL),1,0);
		gridPane.add(createButton("Industrial Overlay", pGrid, PropertySpecificationType.IND_ZONE, OverlayColor.INDUSTRIAL), 1, 1);
		

		return gridPane;
		
	}
	
	//Button Construction Method
	private Button createButton(String text, Grid<?> grid, SpecificationType specificationType, 
			OverlayColor overlayColor) {
		Button button = new Button(text);
		button.setMaxSize(BUTTON_MAX_WIDTH, BUTTON_MAX_HEIGHT);
		button.setMinSize(BUTTON_MAX_WIDTH, BUTTON_MAX_HEIGHT);
		EventController.setOverlayButtonEvents(button, pnTopRightGap, cursor, overlayHandler, grid, specificationType, overlayColor);
		

		return button;
	}
}
