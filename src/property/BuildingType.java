package property;

public enum BuildingType {
	RES_SMALL("R1", 4),
	COM_SMALL("B1", 4),
	IND_SMALL("I1", 6),
	POLICE_STATION("P1", 6);

	private BuildingType(String paneId, Integer maxOccupancy) {
		this.paneId = paneId;
	}
	
	private String paneId;
	private Integer maxOccupancy;

	public String getPaneId() {
		return paneId;
	}

	public void setPaneId(String paneId) {
		this.paneId = paneId;
	}

	public Integer getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupancy(Integer maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
}
