package objects;
import java.util.List;


public class Residential extends Zone {

	public Residential(int xLocation, int yLocation,
			int maxOccupancy) {
		super(xLocation, yLocation, TileType.RESIDENTIAL, maxOccupancy, null);
	}
}
