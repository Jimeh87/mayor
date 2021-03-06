package objects;

public enum OverlayColor {
	POLICE("POverlay"),
	RESIDENTIAL("ROverlay"),
	COMMERCIAL("COverlay"),
	INDUSTRIAL("IOverlay"), 
	FARM("FOverlay");
	
	private String paneId;

	private OverlayColor(String paneId) {
		this.paneId = paneId;
	}

	public String getPaneId() {
		return paneId;
	}
}