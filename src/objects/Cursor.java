package objects;


public class Cursor{
	
	//Fields
	private CursorType cursorType;
	private Integer xLocation;
	private Integer yLocation;
	private Integer isZoningColStart;
	private Integer isZoningRowStart;
	private boolean isZoning = false;
	
	//Construct
	public Cursor(CursorType cursorType) {
		this.cursorType = cursorType;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.isZoningColStart = 0;
		this.isZoningRowStart = 0;
	}
	
	//Methods
	public CursorType getCursorType() {
		return cursorType;
	}
	public void setCursorType(CursorType cursorType) {	
		this.cursorType = cursorType;
	}
	public Integer getXLocation(){
		return xLocation;
	}
	public void setXLocation(Integer xLocation){
		this.xLocation = xLocation;
	}
	public Integer getYLocation(){
		return yLocation;
	}
	public void setYLocation(Integer yLocation){
		this.yLocation = yLocation;
	}

	public boolean isZoning() {
		return isZoning;
	}

	public void setIsZoning(boolean isZoning) {
		this.isZoning = isZoning;
	}

	public Integer getIsZoningColStart() {
		return isZoningColStart;
	}

	public void setIsZoningColStart(Integer isZoningColStart) {
		this.isZoningColStart = isZoningColStart;
	}

	public Integer getIsZoningRowStart() {
		return isZoningRowStart;
	}

	public void setIsZoningRowStart(Integer isZoningRowStart) {
		this.isZoningRowStart = isZoningRowStart;
	}
	
}
