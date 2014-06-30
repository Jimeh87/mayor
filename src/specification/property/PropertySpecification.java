package specification.property;

import grid.Grid;
import specification.Specification;
import specification.SpecificationEntity;
import specification.desirability.DesirabilitySpecification;

public abstract class PropertySpecification extends Specification {
	public abstract String getPaneId() throws IllegalAccessException;
	public abstract void applySplash(Grid<DesirabilitySpecification> dGrid, SpecificationEntity<PropertySpecification> parentEntity);
	public abstract void remove();
	public abstract void tick();
}