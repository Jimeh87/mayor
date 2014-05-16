package property;

import property.specification.ZoneType;

public enum BuildingType {
	RES_SMALL("R0", 4),
	COM_SMALL("C0", 4),
	IND_SMALL("I0", 6),
	POLICE_STATION("P1", 6);

	private BuildingType(String paneId, Integer maxOccupancy) {
		this.paneId = paneId;
		this.maxOccupancy = maxOccupancy;
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
