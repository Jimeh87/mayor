package objects;
import java.util.List;


public abstract class Zone extends Tile {

	public Zone(int xLocation, int yLocation, TileType tileType, int maxOccupancy, List<Person> personList) {
		super(xLocation, yLocation, tileType);
		this.maxOccupancy = maxOccupancy;
		this.setPersonList(personList);
	}

	private Integer buildingId = null;
	private int maxOccupancy = 0;
	private List<Person> personList;
	
	public Integer getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public List<Person> getPersonList() {
		return personList;
	}
	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
}
