package objects;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Used to set up event triggers
 */
public class EventController {
	/** EventControlling Method for GameGrid Tile events
	 * @param txtType the Stats being displayed
	 * @param cursor 
	 * @param tile
	 */
	public static void setTileEvents(final Text txtType, final Cursor cursor, final Tile tile) {
		//Mouse Over Events For GameGrid Display Data In TopLeft GridPane
		tile.getPane().addEventHandler(MouseEvent.MOUSE_MOVED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						String buildingId = null;
						if (cursor.getCursorType() == CursorType.ZONE_BULLDOZE
								&& tile.getTileType().isZone()
								&& tile.isBuildingExists()) {
							
							buildingId = tile.getTileType().getBuildingIdMainType(); //show zone for current building
						} else if (tile.getTileType() == TileType.EMPTY) { //can't add to tile that already exists
							buildingId = cursor.getCursorType().getTileType().getBuildingIdMainType();
						}
						
						if (buildingId != null) {
							tile.getPane().setId(buildingId);
						}
						txtType.setText("Tile Type: " + tile.getTileType().getName());
					}
		});
		tile.getPane().addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						tile.refreshPane(); //Sets Pane with current Tile values
					}
		});
		//Mouse Click Events For GameGrid Filtered by Current Cursor Type
		tile.getPane().addEventHandler(MouseEvent.MOUSE_PRESSED, 
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						if (cursor.getCursorType().isTileTypeExists()) {
							if (cursor.getCursorType() == CursorType.ZONE_BULLDOZE //BULLDOZE LOGIC
									&& tile.getTileType().isZone()
									&& tile.isBuildingExists()) {
								tile.setBuildingIdSubType(null); //just remove building, not zone
							} else if (tile.getTileType() == TileType.EMPTY) { //can't add to tile that already exists
								tile.setTileType(cursor.getCursorType().getTileType());
							}
							tile.refreshPane();
							txtType.setText("Tile Type: " + tile.getTileType().getName());
						}
					}});
	}
	
	public static void setButtonEvents(final Button Btn, final Pane pnTopRightGap, final Cursor cursor, final CursorType newCursorType) {
		//Mouse Click Event sets Cursor to Zoning Tool
		Btn.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						cursor.setCursorType(newCursorType);
						pnTopRightGap.setId(cursor.getCursorType().getCursorIndicator().getZone());
					}
				});	
		
	}}
	
