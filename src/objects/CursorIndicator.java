package objects;

/**
 * @author Josh
 * Used to indicate the type of cursor that is currently in use.
 */
public enum CursorIndicator {
	EMPTY ("emptyTopRight"),
	RESIDENTIAL ("resTopRight"),
	COMMERCIAL ("commTopRight"),
	INDUSTRIAL ("indsTopRight"),
	BULLDOZE ("bulldozeTopRight"),
	POLICE_STATION ("policeStationTopRight");
	
	String zone;
	private CursorIndicator(String zone) {
		this.zone = zone;
	}
	
	/**
	 * Used for setting the top right indicator for cursor type
	 * @return zone
	 */
	public String getZone(){
		return zone;
	}
}
