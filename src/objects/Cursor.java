package objects;


public class Cursor{
	
	//Fields
	CursorType cursorType;
	Integer xLocation;
	Integer yLocation;
	
	//Construct
	public Cursor(CursorType cursorType) {
		this.cursorType = cursorType;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
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
	
}
