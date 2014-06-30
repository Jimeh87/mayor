package specification.property;

import grid.Grid;
import specification.SpecificationEntity;
import specification.desirability.DesirabilitySpecification;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

public class MouseEventSpecification extends PropertySpecification {

	public MouseEventSpecification(EventType<?> eventType, EventHandler<MouseEvent> eventHandler) {
		setEventType(eventType);
		setEventHandler(eventHandler);
	}

	EventHandler<MouseEvent> eventHandler;
	EventType<?> eventType;

	public EventHandler<MouseEvent> getEventHandler() {
		return eventHandler;
	}

	public void setEventHandler(EventHandler<MouseEvent> eventHandler) {
		this.eventHandler = eventHandler;
	}

	public EventType<?> getEventType() {
		return eventType;
	}

	public void setEventType(EventType<?> eventType) {
		this.eventType = eventType;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPaneId() throws IllegalAccessException {
		throw new IllegalAccessException("Pane id is not valid for MouseEventSpecification");
	}

	@Override
	public void applySplash(Grid<DesirabilitySpecification> dGrid,
			SpecificationEntity<PropertySpecification> parentEntity) {
		// TODO Auto-generated method stub
		
	}
	
}
