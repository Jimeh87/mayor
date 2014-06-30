package specification.property.zone;

public enum ZoneType {
	EMPTY("E", false), //SHOULD NOT BE USED IN ZoneSpecification
	RESIDENTIAL("R", false),
	COMMERCIAL("C", false),
	INDUSTRIAL("I", false),
	AGRICULTURAL("TODO", false), //A
	URBAN_SERVICE("TODO", true), //U
	;
	
	private String paneId;
	private boolean autoZoned;

	private ZoneType(String paneId, boolean autoZoned) {
		this.paneId = paneId;
		this.autoZoned = autoZoned;
	}

	public String getPaneId() {
		return paneId;
	}

	public void setPaneId(String paneId) {
		this.paneId = paneId;
	}

	public boolean isAutoZoned() {
		return autoZoned;
	}

	public void setAutoZoned(boolean autoZoned) {
		this.autoZoned = autoZoned;
	}
}
