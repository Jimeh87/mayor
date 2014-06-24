package specification.property;


public enum BuildingType {
	RES_SMALL("R0", 4),
	COM_SMALL("C0", 4),
	IND_SMALL("I0", 6),
	POLICE_STATION("P1", 6);

	private BuildingType(String paneId, int maxOccupancy) {
		this.paneId = paneId;
		this.maxOccupancy = maxOccupancy;
	}
	
	private String paneId;
	private int maxOccupancy;

	public String getPaneId() {
		return paneId;
	}

	public void setPaneId(String paneId) {
		this.paneId = paneId;
	}

	public int getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
}
