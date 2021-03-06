package objects;

import economy.Product;
import grid.Grid;

import java.util.Iterator;
import java.util.Map.Entry;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import specification.SpecificationEntity;
import specification.SpecificationType;
import specification.desirability.DesirabilitySpecification;
import specification.property.MouseEventSpecification;
import specification.property.PropertySpecification;
import specification.property.PropertySpecificationType;
import specification.property.TileSpecification;
import specification.property.building.BuildingSpecification;
import specification.property.building.SupplyAndDemandBuilding;
import specification.property.zone.ZoneSpecification;

/**
 * Used to set up event triggers
 */
public class EventController<T extends PropertySpecification> {
	
	
	public static MouseEventSpecification makeMouseMovedTileEvent(final Cursor cursor, final SpecificationEntity<PropertySpecification> property) {
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				
				TileSpecification tileSpec = (TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE);
				ZoneSpecification zoneSpec = (ZoneSpecification) property.getSpecificationOfType(PropertySpecificationType.ZONE);
				BuildingSpecification buildingSpec = (BuildingSpecification) property.getSpecificationOfType(PropertySpecificationType.BUILDING);

				try {
					if (buildingSpec == null && zoneSpec == null) {
						tileSpec.getTile().setTempPaneId(cursor.getPropertySpecification().getPaneId());
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
			@Override
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
	
	public static MouseEventSpecification makeMousePressedTileEvent(final Cursor cursor, final SpecificationEntity<PropertySpecification> property, final Grid<DesirabilitySpecification> dGrid, final Grid<PropertySpecification> pGrid, final SpecificationEntity<DesirabilitySpecification> desirabilitySpecEntity, GridPane gpGameGrid, int x, int y) {	
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				TileSpecification tileSpec = (TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE);
				ZoneSpecification zoneSpec = (ZoneSpecification) property.getSpecificationOfType(PropertySpecificationType.ZONE);
				BuildingSpecification buildingSpec = (BuildingSpecification) property.getSpecificationOfType(PropertySpecificationType.BUILDING);
				if (cursor.getPropertySpecification() == null) {
					Stage statWindowStage = new Stage(StageStyle.UNDECORATED);
					StatWindowSceneBuilder statWindowSceneBuilder = new StatWindowSceneBuilder(getPopupDetails(tileSpec,buildingSpec,desirabilitySpecEntity),statWindowStage);
					Scene statWindowScene = new Scene(statWindowSceneBuilder.generateStatWindow(),300,300);
					statWindowScene.getStylesheets().add("resources/graphics/statWindowStyle.css");
					statWindowStage.setScene(statWindowScene);
					statWindowStage.centerOnScreen();
					statWindowStage.setResizable(true);
					statWindowStage.setIconified(false);
					statWindowStage.show();
					statWindowStage.focusedProperty().addListener(new ChangeListener<Boolean>()
							{
						
						@Override
						public void changed(ObservableValue<? extends Boolean>source, Boolean wasFocused, Boolean isFocused)
						{
							if (isFocused)
							{
								
							}
							else
								statWindowStage.close();
						}
					});
					return;
				}
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

		};
		
		EventType<MouseEvent> mouseEvent = MouseEvent.MOUSE_PRESSED;
		Pane pane = ((TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
		pane.addEventHandler(mouseEvent, eventHandler);
		return new MouseEventSpecification(mouseEvent, eventHandler);
	}
	
	public static MouseEventSpecification makeMouseMovedTileTextEvent(final Text txtStatusBox, final SpecificationEntity<PropertySpecification> property, final SpecificationEntity<DesirabilitySpecification> desirabilitySpecEntity) {
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				TileSpecification tileSpec = (TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE);
				BuildingSpecification buildingSpec = (BuildingSpecification) property.getSpecificationOfType(PropertySpecificationType.BUILDING);
				txtStatusBox.setText(getPopupDetails(tileSpec, buildingSpec, desirabilitySpecEntity));
			}
		};

		EventType<MouseEvent> mouseEvent = MouseEvent.MOUSE_MOVED;
		Pane pane = ((TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
		pane.addEventHandler(mouseEvent, eventHandler);
		return new MouseEventSpecification(mouseEvent, eventHandler);
	}
	
	private static String getPopupDetails(TileSpecification tileSpec, BuildingSpecification buildingSpec, SpecificationEntity<DesirabilitySpecification> desirabilitySpecEntity) {
		StringBuilder sb = new StringBuilder();
		sb.append(tileSpec.getTile().getPopUpDetails() + "\n");
		
		if (buildingSpec != null) {
			sb.append("Building name: " + buildingSpec.getName() + "\n");
		}
		
		int i = 0;
		sb.append("Mods: \n");
		for (DesirabilitySpecification desirabilitySpec : desirabilitySpecEntity.getSpecificationList()) {
			sb.append(desirabilitySpec.toString() + " ,  ");
			if (++i % 4 == 0) {
				sb.append("\n");
			}
		}
		
		if (buildingSpec != null && SupplyAndDemandBuilding.class.isInstance(buildingSpec)) {
			SupplyAndDemandBuilding supplyAndDemandBuilding = (SupplyAndDemandBuilding) buildingSpec;
			sb.append("\nProduct demand: \n");
			i = 0;
			for (Iterator<Entry<Product, Integer>> it = supplyAndDemandBuilding.getProductDemand().iterator();
					it.hasNext();) {
				Entry<Product, Integer> productDemandEntry = it.next();
				sb.append(productDemandEntry.getKey() + ": " + productDemandEntry.getValue() + " ,  ");
				if (++i % 4 == 0) {
					sb.append("\n");
				}
			}
			
			sb.append("\nProduct for sale: \n");
			i = 0;
			for (Iterator<Entry<Product, Integer>> it = supplyAndDemandBuilding.getProductForSale().iterator();
					it.hasNext();) {
				Entry<Product, Integer> productForSale = it.next();
				sb.append(productForSale.getKey() + ": " + productForSale.getValue() + " ,  ");
				if (++i % 4 == 0) {
					sb.append("\n");
				}
			}
			
		}
		return sb.toString();
	}
	
	public static MouseEventSpecification makeMousePressedTileTextEvent(final Text txtStatusBox, final SpecificationEntity<PropertySpecification> property, final SpecificationEntity<DesirabilitySpecification> desirabilitySpecEntity) {		
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				TileSpecification tileSpec = (TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE);
				BuildingSpecification buildingSpec = (BuildingSpecification) property.getSpecificationOfType(PropertySpecificationType.BUILDING);
				txtStatusBox.setText(getPopupDetails(tileSpec, buildingSpec, desirabilitySpecEntity));
			}
		};
		
		EventType<MouseEvent> mouseEvent = MouseEvent.MOUSE_PRESSED;
		Pane pane = ((TileSpecification) property.getSpecificationOfType(PropertySpecificationType.TILE)).getTile().getPane();
		pane.addEventHandler(mouseEvent, eventHandler);
		return new MouseEventSpecification(mouseEvent, eventHandler);
	}
	
	public static void setButtonEvents(final Button btn, final Pane pnTopRightGap, final Cursor cursor, final Class<? extends PropertySpecification> clazz) {
		//Mouse Click Event sets Cursor to Zoning Tool
		btn.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						try {
							cursor.setPropertySpecification(clazz.newInstance());
							pnTopRightGap.setId(cursor.getPropertySpecification().getPaneId());
						} catch (InstantiationException e1) {
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							e1.printStackTrace();
						} catch (NullPointerException e1) {
							cursor.setPropertySpecification(null);
							pnTopRightGap.setId("emptyTopRight"); //TODO, lazy... will currently be triggered for bulldoze and empty hand
						}
					}
				});	
	}

	public static void setOverlayButtonEvents(Button btn, final Pane pnTopRightGap, final Cursor cursor, final OverlayHandler overlayHandler, Grid<?> grid, final SpecificationType specificationType, OverlayColor overlayColor) {
		btn.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						pnTopRightGap.setId("emptyTopRight");
						cursor.setPropertySpecification(null);
						overlayHandler.displayOverlay(grid, specificationType, overlayColor);
					}
				});
	}

	public static void openOverlaysStage(Button overlaysBtn, final Pane pnTopRightGap, final Cursor cursor, final OverlayHandler overlayHandler, Grid<PropertySpecification> pGrid, Grid<DesirabilitySpecification> dGrid) {
		overlaysBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, 
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						pnTopRightGap.setId("emptyTopRight");
						cursor.setPropertySpecification(null);
						OverlayMenuBuilder overlayMenuBuilder = new OverlayMenuBuilder(pGrid, dGrid,pnTopRightGap,cursor,overlayHandler);
						Scene scene = new Scene(overlayMenuBuilder.generateGridPane(), 300, 200);
						scene.getStylesheets().add("/resources/graphics/overlayMenuStyle.css");
						Stage overlaysStage = new Stage();
						overlaysStage.setScene(scene);
						overlaysStage.setAlwaysOnTop(true);
						overlaysStage.setX(1200);
						overlaysStage.setY(20);
						overlaysStage.show();
						overlaysStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				            @Override
				            public void handle(WindowEvent t) {
				            	overlayHandler.clearOverlay();
				                overlaysStage.close();
				            }

				        });
					}
		});
		
	}

	public static void openOverlaysStageKeyPress(GridPane gridPane,
			Pane pnTopRightGap, Cursor cursor, OverlayHandler overlayHandler,
			Grid<PropertySpecification> pGrid,
			Grid<DesirabilitySpecification> dGrid) {
		// TODO Auto-generated method stub Overlay Open Key Press
		gridPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode()==KeyCode.O){
					pnTopRightGap.setId("emptyTopRight");
					cursor.setPropertySpecification(null);
					OverlayMenuBuilder overlayMenuBuilder = new OverlayMenuBuilder(pGrid, dGrid,pnTopRightGap,cursor,overlayHandler);
					Scene scene = new Scene(overlayMenuBuilder.generateGridPane(), 300, 200);
					scene.getStylesheets().add("/resources/graphics/overlayMenuStyle.css");
					Stage overlaysStage = new Stage();
					overlaysStage.setScene(scene);
					overlaysStage.setAlwaysOnTop(true);
					overlaysStage.setX(1200);
					overlaysStage.setY(20);
					overlaysStage.show();
					overlaysStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			            @Override
			            public void handle(WindowEvent t) {
			            	overlayHandler.clearOverlay();
			                overlaysStage.close();
			            }

			        });
					System.out.println(ke);
				}
			}
		});
	}

}
