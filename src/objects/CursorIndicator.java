package objects;

public enum CursorIndicator {
	EMPTY ("emptyTopRight"),
	RESIDENTIAL ("resTopRight"),
	COMMERCIAL ("commTopRight"),
	INDUSTRIAL ("indsTopRight");
	
	String z;
	private CursorIndicator(String z) {
		this.z = z;
	}
	
	public String getZone(){
		return z;
	}
}
