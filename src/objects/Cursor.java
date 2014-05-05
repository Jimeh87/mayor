package objects;


public class Cursor{
	
	//Fields
	private CursorType cursorType;
	private Integer xLocation;
	private Integer yLocation;
	private boolean mouseHeld = false;
	
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

	public boolean isMouseHeld() {
		return mouseHeld;
	}

	public void setMouseHeld(boolean mouseHeld) {
		this.mouseHeld = mouseHeld;
	}
	
}
