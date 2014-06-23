package property.specification;

import objects.SpecificationEntity;
import objects.Tile;
import property.PropertySpecification;
import desirability.specification.DesirabilitySpecification;

public class TileSpecification extends PropertySpecification {
	
	private SpecificationEntity<DesirabilitySpecification> desirabilitySpecificationEntity = new SpecificationEntity<DesirabilitySpecification>();
	
	public TileSpecification(Tile tile) {
		setTile(tile);
	}
	
	private Tile tile;
	
	public Tile getTile() {
		return tile;
	}
	
	public void setTile(Tile tile) {
		if (tile == null) {
			throw new IllegalArgumentException("Tile is a required field in the TileSpecification");
		}
		this.tile = tile;
	}

	public SpecificationEntity<DesirabilitySpecification> getDesirabilitySpecificationEntity() {
		return desirabilitySpecificationEntity;
	}
	public void setDesirabilitySpecificationEntity(
			SpecificationEntity<DesirabilitySpecification> desirabilitySpecificationEntity) {
		this.desirabilitySpecificationEntity = desirabilitySpecificationEntity;
	}
	
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
