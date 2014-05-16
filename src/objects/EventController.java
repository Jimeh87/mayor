package objects;

import property.BuildingType;
import property.Property;
import property.specification.BuildingSpecification;
import property.specification.PropertySpecificationType;
import property.specification.TileSpecification;
import property.specification.ZoneSpecification;
import property.specification.ZoneType;
import property.specification.MouseEventSpecification;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Used to set up event triggers
 */
public class EventController {
	
	public static MouseEventSpecification makeMouseMovedTileEvent(final Cursor cursor, final Property property) {
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				TileSpecification tileSpec = (TileSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.TILE);
				ZoneSpecification zoneSpec = (ZoneSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.ZONE);
				BuildingSpecification buildingSpec = (BuildingSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.BUILDING);

				String paneId;
				if (cursor.getCursorType() == CursorType.ZONE_BULLDOZE) {
					paneId = getBuildingIdForBulldoze(zoneSpec, buildingSpec);
				} else if (zoneSpec != null || buildingSpec != null) {
					paneId = null;
				} else if (cursor.getCursorType() == CursorType.ZONE_EMPTY) {
					paneId = null;
				} else {
					paneId = cursor.getCursorType().getPaneId();
				}
						
				if (paneId != null) {
					tileSpec.getTile().getPane().setId(paneId);
				}
			}
		};

		Pane pane = ((TileSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
		EventType<MouseEvent> mouseEvent = MouseEvent.MOUSE_MOVED;
		pane.addEventHandler(mouseEvent, eventHandler);
		return new MouseEventSpecification(mouseEvent, eventHandler);	
	}
	
	public static MouseEventSpecification makeMouseExitedTileEvent(final Property property) {
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				TileSpecification tileSpec = (TileSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.TILE);
				tileSpec.getTile().refreshPane();					
			}
		};

		Pane pane = ((TileSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
		EventType<MouseEvent> mouseEvent = MouseEvent.MOUSE_EXITED;
		pane.addEventHandler(mouseEvent, eventHandler);
		return new MouseEventSpecification(mouseEvent, eventHandler);
	}
	
	public static MouseEventSpecification makeMousePressedTileEvent(final Cursor cursor, final Property property) {	
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				TileSpecification tileSpec = (TileSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.TILE);
				ZoneSpecification zoneSpec = (ZoneSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.ZONE);
				BuildingSpecification buildingSpec = (BuildingSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.BUILDING);

				String paneId;
				if (cursor.getCursorType() == CursorType.ZONE_BULLDOZE) {
					paneId = getBuildingIdForBulldoze(zoneSpec, buildingSpec);
					if (buildingSpec != null) {
						property.removeSpecification(buildingSpec);
					} else if (zoneSpec != null) {
						property.removeSpecification(zoneSpec);
					}
				} else if (zoneSpec != null || buildingSpec != null) {
					paneId = null;
				} else if (cursor.getCursorType() == CursorType.ZONE_EMPTY) {
					paneId = null;
				} else {
					paneId = cursor.getCursorType().getPaneId();
					if (cursor.getCursorType().getZoneType() != null) {
						property.addSpecification(new ZoneSpecification(cursor.getCursorType().getZoneType()));
					} else if (cursor.getCursorType().getBuildingType() != null) {
						property.addSpecification(new BuildingSpecification(cursor.getCursorType().getBuildingType(), 0));
					} else {
						throw new IllegalStateException("Should never get here. Logic error.");
					}
				}
				
				if (paneId != null) {
					tileSpec.getTile().setPaneId(paneId);
					tileSpec.getTile().refreshPane();
				}
			}
		};
		
		EventType<MouseEvent> mouseEvent = MouseEvent.MOUSE_PRESSED;
		Pane pane = ((TileSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
		pane.addEventHandler(mouseEvent, eventHandler);
		return new MouseEventSpecification(mouseEvent, eventHandler);
	}
	
	private static String getBuildingIdForBulldoze(ZoneSpecification zoneSpec, BuildingSpecification buildingSpec) {
		String buildingId;
		if (zoneSpec != null && buildingSpec != null) {
			buildingId = zoneSpec.getZoneType().getPaneId();
		} else if (zoneSpec == null || buildingSpec == null) {
			buildingId = ZoneType.EMPTY.getPaneId();
		} else {
			throw new IllegalStateException("Should never get here. Logic error.");
		}
		return buildingId;
	}
	
	public static MouseEventSpecification makeMouseMovedTileTextEvent(final Text txtStatusBox, final Property property) {
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				TileSpecification tileSpec = (TileSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.TILE);
				txtStatusBox.setText(tileSpec.getTile().getPopUpDetails());
			}
		};

		EventType<MouseEvent> mouseEvent = MouseEvent.MOUSE_MOVED;
		Pane pane = ((TileSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
		pane.addEventHandler(mouseEvent, eventHandler);
		return new MouseEventSpecification(mouseEvent, eventHandler);
	}
	
	public static MouseEventSpecification makeMousePressedTileTextEvent(final Text txtStatusBox, final Property property) {		
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				TileSpecification tileSpec = (TileSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.TILE);
				txtStatusBox.setText(tileSpec.getTile().getPopUpDetails());
			}
		};
		
		EventType<MouseEvent> mouseEvent = MouseEvent.MOUSE_PRESSED;
		Pane pane = ((TileSpecification) property.getPropertySpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
		pane.addEventHandler(mouseEvent, eventHandler);
		return new MouseEventSpecification(mouseEvent, eventHandler);
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
	
