package objects;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class EventController {
	public static void setTileEvents(final Pane pnGameGrid, final Text txtType, final String cursorType, final Tile tile) {
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
						if (cursorType.equals("zoneEmpty")) {
							tile.setTileType(TileType.EMPTY);
						} else if (cursorType.equals("zoneRes")) {
							tile.setTileType(TileType.RESIDENTIAL);
						} else if (cursorType.equals("zoneComm")) {
							tile.setTileType(TileType.COMMERCIAL);
						} else if (cursorType.equals("zoneInds")) {
							tile.setTileType(TileType.INDUSTRIAL);
						}
						pnGameGrid.setId(tile.getTileType().getId());
					}
		});
	}
	
	public static void setButtonEvents() {
		
	}
}
