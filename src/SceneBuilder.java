import grid.Grid;
import grid.GridIterator;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import objects.City;
import objects.Cursor;
import objects.EventController;
import objects.OverlayHandler;
import objects.Tile;
import specification.SpecificationEntity;
import specification.desirability.DesirabilitySpecification;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.TileSpecification;
import specification.property.building.PoliceStationSpecification;
import specification.property.zone.AgriculturalZoneSpecification;
import specification.property.zone.CommercialZoneSpecification;
import specification.property.zone.IndustrialZoneSpecification;
import specification.property.zone.ResidentialZoneSpecification;


public class SceneBuilder {
	// Grid Pane Constructor and Grid Constraints UI Spacing
	// Returns GridPane to main scene Uses City for ini info
	// Style info set by gameGridStyle.css in resources.graphics
	// Event Handler for UI interaction
		Parent generateGridPane(City city, Cursor cursor) {
			
			GridPane gridPane = new GridPane();
			gridPane.setHgap(1);gridPane.setVgap(1);
			
			//Top Gap Spacers 
			//Top Left GridPane used for MouseOver info
			GridPane pnLeftGap = new GridPane();
			pnLeftGap.setMinSize(240, 135);
			pnLeftGap.setMaxSize(240, 135);
			pnLeftGap.setId("topLeftBar");
			gridPane.add(pnLeftGap, 0, 0);
			
			GridPane pnTopGap = new GridPane();
			pnTopGap.setMinSize(1440, 135);
			pnTopGap.setMaxSize(1440, 135);
			pnTopGap.setId("topBar");
			
			gridPane.add(pnTopGap, 1, 0);
			final Text txtType = new Text();
			txtType.setText("Tile Type: ");
			pnTopGap.add(txtType, 0, 0);
			
			
			final Pane pnTopRightGap = new Pane();
			pnTopRightGap.setMinSize(240, 135);
			pnTopRightGap.setMaxSize(240, 135);
			pnTopRightGap.setId("emptyTopRight");
			gridPane.add(pnTopRightGap, 2, 0);
			
			//Main Game GridPane set from City Event Controls for changing tile type
			Grid<DesirabilitySpecification> dGrid = city.getEconomy().getDesirabilityGrid();
			GridPane gpGameGrid = new GridPane();
			GridIterator<PropertySpecification> gridIterator = city.getGrid().iterator();
			while (gridIterator.hasNext()) {
				final SpecificationEntity<PropertySpecification> property = gridIterator.next();
				property.addSpecification(new TileSpecification(new Tile(gridIterator.getX(), gridIterator.getY())));
				property.addSpecification(EventController.makeMouseMovedTileEvent(cursor, property));
				property.addSpecification(EventController.makeMouseExitedTileEvent(property));
				property.addSpecification(EventController.makeMousePressedTileEvent(cursor, property, dGrid, city.getGrid()));
				property.addSpecification(EventController.makeMouseMovedTileTextEvent(txtType, property, dGrid.getSpecificationEntity(gridIterator.getX(), gridIterator.getY())));
				property.addSpecification(EventController.makeMousePressedTileTextEvent(txtType, property, dGrid.getSpecificationEntity(gridIterator.getX(), gridIterator.getY())));
				Tile tile = ((TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE)).getTile();
				//Add Pane to GameGrid
				gpGameGrid.add(tile.getOverlayPane(), gridIterator.getX(), gridIterator.getY());	
				gpGameGrid.add(tile.getPane(), gridIterator.getX(), gridIterator.getY());	 
			}
			gridPane.add(gpGameGrid, 1, 1);
			
			 
			//Control GridPane 
			GridPane gpControlGrid = new GridPane();
			gpControlGrid.setId("controlGrid");
			gpControlGrid.setMinSize(235, 540);
			gridPane.add(gpControlGrid, 2, 1);
			
			//Empty Zone Button Setup and Event Handle
			Button emptyBtn = new Button();
			emptyBtn.setId("emptyButton");
			emptyBtn.setMinSize(235,30);
			emptyBtn.setMaxSize(235,30);
			emptyBtn.setText("Empty Hand");
			//Event Controller Call Zone Empty
			EventController.setButtonEvents(emptyBtn, pnTopRightGap, cursor, null);
			//Add Button To Control Grid
			gpControlGrid.add(emptyBtn, 0,0);
			
			//TODO: change class parms to enums
			//Zone Residential Button Setup and Event handle
			Button resBtn = new Button();
			resBtn.setId("resButton");
			resBtn.setMinSize(235,30);
			resBtn.setMaxSize(235,30);
			resBtn.setText("Zone Residential");
			//Event Controller Call Zone Residential
			EventController.setButtonEvents(resBtn, pnTopRightGap, cursor, ResidentialZoneSpecification.class);
			//Add Button to Control Grid
			gpControlGrid.add(resBtn, 0,1);
			
			//Zone Commercial Button Setup and Event handle
			Button commBtn = new Button();
			commBtn.setId("commButton");
			commBtn.setMinSize(235,30);
			commBtn.setMaxSize(235,30);
			commBtn.setText("Zone Commercial");
			EventController.setButtonEvents(commBtn, pnTopRightGap, cursor, CommercialZoneSpecification.class);
			//Add Button to Control Grid
			gpControlGrid.add(commBtn, 0, 2);
			
			//Zone Industrial Button Setup and Event handle
			Button indsBtn = new Button();
			indsBtn.setId("indsButton");
			indsBtn.setMinSize(235,30);
			indsBtn.setMaxSize(235,30);
			indsBtn.setText("Zone Industrial");
			EventController.setButtonEvents(indsBtn, pnTopRightGap, cursor, IndustrialZoneSpecification.class);
			//Add Button to Control Grid
			gpControlGrid.add(indsBtn, 0, 3);
			
			//Zone Farm Button Setup and Event handle
			Button agriculturalBtn = new Button();
			agriculturalBtn.setId("agButton");
			agriculturalBtn.setMinSize(235,30);
			agriculturalBtn.setMaxSize(235,30);
			agriculturalBtn.setText("Zone Agricultural");
			EventController.setButtonEvents(agriculturalBtn, pnTopRightGap, cursor, AgriculturalZoneSpecification.class);
			//Add Button to Control Grid
			gpControlGrid.add(agriculturalBtn, 0, 4);
			
			//Police Button Setup and Event handle
			Button policeStationBtn = new Button();
			policeStationBtn.setId("policeButton");
			policeStationBtn.setMinSize(235,30);
			policeStationBtn.setMaxSize(235,30);
			policeStationBtn.setText("Police Station");
			EventController.setButtonEvents(policeStationBtn, pnTopRightGap, cursor, PoliceStationSpecification.class);
			//Add Button to Control Grid
			gpControlGrid.add(policeStationBtn, 0, 5);
			
			//TODO: passing in null like empty hand, this is because the way it works needs to be re-thought
			//Bulldoze Button Setup and Event handle
			Button bulldozeBtn = new Button();
			bulldozeBtn.setId("bulldozeButton");
			bulldozeBtn.setMinSize(235,30);
			bulldozeBtn.setMaxSize(235,30);
			bulldozeBtn.setText("Bulldoze Zone");
			//Event Controller Call Zone Bulldoze
			EventController.setButtonEvents(bulldozeBtn, pnTopRightGap, cursor, null);
			//Add Button To Control Grid
			gpControlGrid.add(bulldozeBtn, 0, 6);
			
			//overlays
			OverlayHandler overlayHandler = new OverlayHandler(city.getGrid());
			
			Button overlaysBtn = new Button();
			overlaysBtn.setId("OverlaysButton");
			overlaysBtn.setMinSize(235, 30);
			overlaysBtn.setMaxSize(235, 30);
			overlaysBtn.setText("Open Overlays");
			EventController.openOverlaysStage(overlaysBtn, pnTopRightGap, cursor, overlayHandler, city.getGrid(), city.getEconomy().getDesirabilityGrid());
			gpControlGrid.add(overlaysBtn, 0, 7);
			
			return gridPane;
		}
}
