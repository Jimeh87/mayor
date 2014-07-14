package specification.property.building;


public enum BuildingType {
	RES_SMALL("R0"),
	COM_SMALL("C0"),
	IND_SMALL("I0"),
	FARM("F0"),
	POLICE_STATION("P1");

	private BuildingType(String paneId) {
		this.paneId = paneId;
	}
	
	private String paneId;

	public String getPaneId() {
		return paneId;
	}

	public void setPaneId(String paneId) {
		this.paneId = paneId;
	}
}
