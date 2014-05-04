package objects;
import java.util.List;


public class Commercial extends Zone {

	public Commercial(int xLocation, int yLocation, Integer buildingId,
			int maxOccupancy, List<Person> personList) {
		super(xLocation, yLocation, TileType.COMMERCIAL, maxOccupancy, personList);
		// TODO Auto-generated constructor stub
	}


}
