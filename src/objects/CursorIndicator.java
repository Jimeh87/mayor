package objects;

public enum CursorIndicator {
	EMPTY ("emptyTopRight"),
	RESIDENTIAL ("resTopRight"),
	COMMERCIAL ("commTopRight"),
	INDUSTRIAL ("indsTopRight"),
	BULLDOZE ("bulldozeTopRight");
	
	String z;
	private CursorIndicator(String z) {
		this.z = z;
	}
	
	public String getZone(){
		return z;
	}
}
