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
	//Define
	private static final double BUTTON_MAX_WIDTH = 150;
	private static final double BUTTON_MAX_HEIGHT = 50;
	private Grid<DesirabilitySpecification> dGrid;
	private Grid<PropertySpecification> pGrid;
	private Pane pnTopRightGap;
	private Cursor cursor;
	private OverlayHandler overlayHandler;
	
	//Construct
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
 
		gridPane.add(createButton("Police", dGrid, DesirabilitySpecificationType.POLICE, OverlayColor.POLICE),0,0);
		gridPane.add(createButton("Residential", pGrid, PropertySpecificationType.RES_ZONE, OverlayColor.RESIDENTIAL), 0, 1);
		gridPane.add(createButton("Commmercial", pGrid, PropertySpecificationType.COM_ZONE, OverlayColor.COMMERCIAL),1,0);
		gridPane.add(createButton("Industrial", pGrid, PropertySpecificationType.IND_ZONE, OverlayColor.INDUSTRIAL), 1, 1);
		gridPane.add(createButton("Farming", pGrid, PropertySpecificationType.FARM_ZONE,OverlayColor.FARM), 0, 2);
		
		//Return Node as Built Scene
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
