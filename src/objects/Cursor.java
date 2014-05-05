package objects;


public class Cursor{
	
	//Fields
	String cursorType;
	Integer xLocation;
	Integer yLocation;
	
	//Construct
	public Cursor(String type) {
		this.cursorType = type;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
	}
	
	//Methods
	public String getCursorType() {
		return cursorType;
	}
	public void setCursorType(String type) {	
		this.cursorType = type;
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
