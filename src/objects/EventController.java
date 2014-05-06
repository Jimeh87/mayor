package objects;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class EventController {
	/** EventControlling Method for GameGrid Tile events
	 * @param pnGameGrid the Pane that is clicked
	 * @param txtType the Stats being displayed
	 * @param cursor 
	 * @param tile
	 */
	public static void setTileEvents(final Text txtType, final Cursor cursor, final Tile tile) {
		//Mouse Over Events For GameGrid Display Data In TopLeft GridPane
		tile.getPane().addEventHandler(MouseEvent.MOUSE_MOVED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						txtType.setText("Tile Type: " + tile.getTileType().getId());
						if (cursor.getCursorType().getTileType() != null) {
							tile.getPane().setId(cursor.getCursorType().getTileType().getId());
						}
					}
		});
		tile.getPane().addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						txtType.setText("Tile Type:");
						tile.getPane().setId(tile.getTileType().getId());
					}
		});
		tile.getPane().addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						tile.getPane().setId(tile.getTileType().getId());
					}	
		});
		//Mouse Click Events For GameGrid Filtered by Current Cursor Type
		tile.getPane().addEventHandler(MouseEvent.MOUSE_PRESSED, 
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						if (cursor.getCursorType().getTileType() != null) {
							tile.setTileType(cursor.getCursorType().getTileType());
							tile.getPane().setId(tile.getTileType().getId());
							txtType.setText("Tile Type: " + tile.getTileType().getId());
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
	
