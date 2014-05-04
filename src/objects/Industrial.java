package objects;
import java.util.List;


public class Industrial extends Zone {

	public Industrial(int xLocation, int yLocation, Integer buildingId,
			int maxOccupancy, List<Person> personList) {
		super(xLocation, yLocation, TileType.INDUSTRIAL, maxOccupancy, personList);
		// TODO Auto-generated constructor stub
	}


}
