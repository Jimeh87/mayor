package objects;

import grid.Grid;
import grid.GridIterator;

import java.util.List;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import specification.SpecificationEntity;
import specification.desirability.DesirabilitySpecification;
import specification.desirability.DesirabilitySpecificationType;
import specification.property.MouseEventSpecification;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.TileSpecification;
import specification.property.building.BuildingSpecification;
import specification.property.building.BuildingType;
import specification.property.zone.ZoneSpecification;
import specification.property.zone.ZoneType;

/**
 * Used to set up event triggers
 */
public class EventController<T extends PropertySpecification> {
	
	public static MouseEventSpecification makeMouseMovedTileEvent(final Cursor cursor, final SpecificationEntity<PropertySpecification> property) {
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				
				//not needed right now, will be when bulldoze is readded
				TileSpecification tileSpec = (TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE);
				ZoneSpecification zoneSpec = (ZoneSpecification) property.getSpecificationOfType(PropertySpecificationType.ZONE);
				BuildingSpecification buildingSpec = (BuildingSpecification) property.getSpecificationOfType(PropertySpecificationType.BUILDING);

				try {
					if (buildingSpec == null && zoneSpec == null) {
						tileSpec.getTile().getPane().setId(cursor.getPropertySpecification().getPaneId());
					}
				} catch (NullPointerException e1) {
					//swallow
				} catch (IllegalAccessException e1) {
					//This should never happen
					e1.printStackTrace();
				}
			}
		};

		Pane pane = ((TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
		EventType<MouseEvent> mouseEvent = MouseEvent.MOUSE_MOVED;
		pane.addEventHandler(mouseEvent, eventHandler);
		return new MouseEventSpecification(mouseEvent, eventHandler);	
	}
	
	public static MouseEventSpecification makeMouseExitedTileEvent(final SpecificationEntity<PropertySpecification> property) {
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				TileSpecification tileSpec = (TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE);
				tileSpec.getTile().refreshPane();					
			}
		};

		Pane pane = ((TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
		EventType<MouseEvent> mouseEvent = MouseEvent.MOUSE_EXITED;
		pane.addEventHandler(mouseEvent, eventHandler);
		return new MouseEventSpecification(mouseEvent, eventHandler);
	}
	
	public static MouseEventSpecification makeMousePressedTileEvent(final Cursor cursor, final SpecificationEntity<PropertySpecification> property, final Grid<DesirabilitySpecification> dGrid, final Grid<PropertySpecification> pGrid) {	
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				TileSpecification tileSpec = (TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE);
				ZoneSpecification zoneSpec = (ZoneSpecification) property.getSpecificationOfType(PropertySpecificationType.ZONE);
				BuildingSpecification buildingSpec = (BuildingSpecification) property.getSpecificationOfType(PropertySpecificationType.BUILDING);

				try {
					if (zoneSpec == null && buildingSpec == null) {
						property.addSpecification(cursor.getPropertySpecification());
						tileSpec.getTile().setPaneId(cursor.getPropertySpecification().getPaneId());
						tileSpec.getTile().refreshPane();
						cursor.getPropertySpecification().applySplash(dGrid, property);
					}
				} catch (NullPointerException e1) {
					//swallow
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
			}
			
			/*	//test stuff
			GridIterator<DesirabilitySpecification> gIterator = dGrid.iterator();
			while (gIterator.hasNext()) {
				SpecificationEntity<DesirabilitySpecification> dEntity = gIterator.next();
				List<DesirabilitySpecification> policeSpecList = dEntity.getSpecificationListOfType(DesirabilitySpecificationType.POLICE);
				if (!policeSpecList.isEmpty()) {
					TileSpecification tileSpec2 = (TileSpecification) pGrid.getSpecificationEntity(gIterator.getX(), gIterator.getY()).getSpecificationOfType(PropertySpecificationType.TILE);
					tileSpec2.getTile().setPaneId(BuildingType.COM_SMALL.getPaneId());
					tileSpec2.getTile().refreshPane();
					tileSpec2.getTile().lock();
				}
			} */
		};
		
		EventType<MouseEvent> mouseEvent = MouseEvent.MOUSE_PRESSED;
		Pane pane = ((TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
		pane.addEventHandler(mouseEvent, eventHandler);
		return new MouseEventSpecification(mouseEvent, eventHandler);
	}
	
	public static MouseEventSpecification makeMouseMovedTileTextEvent(final Text txtStatusBox, final SpecificationEntity<PropertySpecification> property, final SpecificationEntity<DesirabilitySpecification> desirabilitySpecEntity) {
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				TileSpecification tileSpec = (TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE);
				txtStatusBox.setText(getPopupDetails(tileSpec, desirabilitySpecEntity));
			}
		};

		EventType<MouseEvent> mouseEvent = MouseEvent.MOUSE_MOVED;
		Pane pane = ((TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
		pane.addEventHandler(mouseEvent, eventHandler);
		return new MouseEventSpecification(mouseEvent, eventHandler);
	}
	
	private static String getPopupDetails(TileSpecification tileSpec, SpecificationEntity<DesirabilitySpecification> desirabilitySpecEntity) {
		StringBuilder sb = new StringBuilder();
		sb.append(tileSpec.getTile().getPopUpDetails() + "\n");
		int i = 0;
		sb.append("Mods: \n");
		for (DesirabilitySpecification desirabilitySpec : desirabilitySpecEntity.getSpecificationList()) {
			sb.append(desirabilitySpec.toString() + " ,  ");
			if (++i % 4 == 0) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	public static MouseEventSpecification makeMousePressedTileTextEvent(final Text txtStatusBox, final SpecificationEntity<PropertySpecification> property, final SpecificationEntity<DesirabilitySpecification> desirabilitySpecEntity) {		
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				TileSpecification tileSpec = (TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE);
				txtStatusBox.setText(getPopupDetails(tileSpec, desirabilitySpecEntity));
			}
		};
		
		EventType<MouseEvent> mouseEvent = MouseEvent.MOUSE_PRESSED;
		Pane pane = ((TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
		pane.addEventHandler(mouseEvent, eventHandler);
		return new MouseEventSpecification(mouseEvent, eventHandler);
	}
	
	public static void setButtonEvents(final Button Btn, final Pane pnTopRightGap, final Cursor cursor, final Class<? extends PropertySpecification> clazz) {
		//Mouse Click Event sets Cursor to Zoning Tool
		Btn.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						try {
							cursor.setPropertySpecification(clazz.newInstance());
							pnTopRightGap.setId(cursor.getPropertySpecification().getPaneId());
						} catch (InstantiationException e1) {
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							e1.printStackTrace();
						} catch (NullPointerException e1) {
							pnTopRightGap.setId("emptyTopRight"); //TODO, lazy... will currently be triggered for bulldoze and empty hand
						}
					}
				});	
	}}
