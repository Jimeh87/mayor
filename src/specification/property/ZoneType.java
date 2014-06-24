package specification.property;

public enum ZoneType {
	EMPTY("E"), //SHOULD NOT BE USED IN ZoneSpecification
	RESIDENTIAL("R"),
	COMMERCIAL("C"),
	INDUSTRIAL("I");
	
	private String paneId;

	private ZoneType(String paneId) {
		this.paneId = paneId;
	}

	public String getPaneId() {
		return paneId;
	}

	public void setPaneId(String paneId) {
		this.paneId = paneId;
	}
}
