import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import objects.Cursor;
import objects.Tile;
import objects.TileType;


public class SceneBuilder {
	// Grid Pane Constructor and Grid Constraints UI Spacing
	// Returns GridPane to main scene Uses City for ini info
	// Style info set by gameGridStyle.css in resources.graphics
	// Event Handler for UI interaction
		Parent generateGridPane(City city) {
			
			final Cursor cursor = new Cursor("empty");
			GridPane gridPane = new GridPane();
			gridPane.setHgap(1);gridPane.setVgap(1);
			
			//Top Gap Spacers 
			//Top Left GridPane used for MouseOver info
			GridPane pnLeftGap = new GridPane();
			pnLeftGap.setMinSize(240, 135);
			pnLeftGap.setMaxSize(240, 135);
			pnLeftGap.setId("topLeftBar");
			gridPane.add(pnLeftGap, 0, 0);
			
			final Text txtType = new Text();
			txtType.setText("Tile Type: ");
			pnLeftGap.add(txtType, 0, 0);
			
			Pane pnTopGap = new Pane();
			pnTopGap.setMinSize(1440, 135);
			pnTopGap.setMaxSize(1440, 135);
			pnTopGap.setId("topBar");
			gridPane.add(pnTopGap, 1, 0);
			
			final Pane pnTopRightGap = new Pane();
			pnTopRightGap.setMinSize(240, 135);
			pnTopRightGap.setMaxSize(240, 135);
			pnTopRightGap.setId("emptyTopRight");
			gridPane.add(pnTopRightGap, 2, 0);
			
			//Main Game GridPane set from City Event Controls for changing tile type
			GridPane gpGameGrid = new GridPane();
			for (int gridY = 0; gridY < 28; gridY++) {
				for (int gridX = 0; gridX < 45; gridX++) {
					final Pane pnGameGrid = new Pane();
					pnGameGrid.setMinSize(32, 32);
					pnGameGrid.setMaxSize(32,  32);
					final Tile tile = city.getGrid().getTile(gridX, gridY);
					pnGameGrid.setId(tile.getTileType().getId());
					//Mouse Over Events For GameGrid Display Data In TopLeft GridPane
					pnGameGrid.addEventHandler(MouseEvent.MOUSE_ENTERED,
							new EventHandler<MouseEvent>() {
								public void handle(MouseEvent e) {
									txtType.setText("Tile Type: " + tile.getTileType().getId());
								}
							});
					pnGameGrid.addEventHandler(MouseEvent.MOUSE_EXITED,
							new EventHandler<MouseEvent>() {
								public void handle(MouseEvent e) {
									txtType.setText("Tile Type:");
								}
					});
					//Mouse Click Events For GameGrid Filtered by Current Cursor Type
					pnGameGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, 
							new EventHandler<MouseEvent>() {
								public void handle(MouseEvent e) {
									if (cursor.getCursorType() == "zoneEmpty") {
										tile.setTileType(TileType.EMPTY);
									}
									if (cursor.getCursorType() == "zoneRes") {
										tile.setTileType(TileType.RESIDENTIAL);
									}
									if (cursor.getCursorType() == "zoneComm") {
										tile.setTileType(TileType.COMMERCIAL);
									}
									if (cursor.getCursorType() == "zoneInds") {
										tile.setTileType(TileType.INDUSTRIAL);
									}
									pnGameGrid.setId(tile.getTileType().getId());
								}
					});
					gpGameGrid.add(pnGameGrid, gridX, gridY);
				}
				 
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
			emptyBtn.setText("Zone Empty");
			//Mouse Click Event sets Cursor to Zone Empty
			emptyBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							pnTopRightGap.setId("emptyTopRight");
							cursor.setCursorType("zoneEmpty");
						}
					});	
			gpControlGrid.add(emptyBtn, 0,0);
			
			//Zone Residential Button Setup and Event handle
			Button resBtn = new Button();
			resBtn.setId("resButton");
			resBtn.setMinSize(235,30);
			resBtn.setMaxSize(235,30);
			resBtn.setText("Zone Residential");
			//Mouse Click Event sets Cursor to Zone Residential
			resBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							pnTopRightGap.setId("resTopRight");
							cursor.setCursorType("zoneRes");
						}
					});	
			gpControlGrid.add(resBtn, 0,1);
			
			//Zone Commercial Button Setup and Event handle
			Button commBtn = new Button();
			commBtn.setId("commButton");
			commBtn.setMinSize(235,30);
			commBtn.setMaxSize(235,30);
			commBtn.setText("Zone Commercial");
			//Mouse Click Event sets Cursor to Zone Commercial
			commBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							pnTopRightGap.setId("commTopRight");
							cursor.setCursorType("zoneComm");
				}
			});	
			gpControlGrid.add(commBtn, 0, 2);
			
			//Zone Industrial Button Setup and Event handle
			Button indsBtn = new Button();
			indsBtn.setId("indsButton");
			indsBtn.setMinSize(235,30);
			indsBtn.setMaxSize(235,30);
			indsBtn.setText("Zone Industrial");
			//Mouse Click Event sets Cursor to Zone Industrial
			indsBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							pnTopRightGap.setId("indsTopRight");
							cursor.setCursorType("zoneInds");
				}
			});	
			gpControlGrid.add(indsBtn, 0, 3);
			
			return gridPane;
		}
}
