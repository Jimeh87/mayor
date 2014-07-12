package objects;

public enum OverlayColor {
	POLICE("POverlay"),
	RESIDENTIAL("ROverlay"),
	COMMERCIAL("COverlay");
	
	private String paneId;

	private OverlayColor(String paneId) {
		this.paneId = paneId;
	}

	public String getPaneId() {
		return paneId;
	}
}