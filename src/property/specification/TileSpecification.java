package property.specification;

import objects.Tile;
import property.PropertySpecification;

public class TileSpecification extends PropertySpecification {
	
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

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
