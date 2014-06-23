package objects;


public enum PersonType {
	entrepreneur (4, 8, 8, 0, 4),
	worker       (4, 0, 0, 4, 9);
	
	private Integer emptyResDemand;
	private Integer emptyBusDemand;
	private Integer emptyIndDemand;
	
	private Integer unoccupiedResDemand;
	private Integer unoccupiedJobsDemand;
	private PersonType(Integer emptyResDemand, Integer emptyBusDemand, Integer emptyIndDemand, Integer unoccupiedResDemand, Integer unoccupiedJobsDemand) {
		this.emptyResDemand = emptyResDemand;
		this.emptyBusDemand = emptyBusDemand;
		this.emptyIndDemand = emptyIndDemand;
		
		this.unoccupiedResDemand = unoccupiedResDemand;
		this.unoccupiedJobsDemand = unoccupiedJobsDemand;
	}
	public Integer getEmptyResDemand() {
		return emptyResDemand;
	}
	public void setEmptyResDemand(Integer emptyResDemand) {
		this.emptyResDemand = emptyResDemand;
	}
	public Integer getEmptyBusDemand() {
		return emptyBusDemand;
	}
	public void setEmptyBusDemand(Integer emptyBusDemand) {
		this.emptyBusDemand = emptyBusDemand;
	}
	public Integer getEmptyIndDemand() {
		return emptyIndDemand;
	}
	public void setEmptyIndDemand(Integer emptyIndDemand) {
		this.emptyIndDemand = emptyIndDemand;
	}
	public Integer getUnoccupiedResDemand() {
		return unoccupiedResDemand;
	}
	public void setUnoccupiedResDemand(Integer unoccupiedResDemand) {
		this.unoccupiedResDemand = unoccupiedResDemand;
	}
	public Integer getUnoccupiedJobsDemand() {
		return unoccupiedJobsDemand;
	}
	public void setUnoccupiedJobsDemand(Integer unoccupiedJobsDemand) {
		this.unoccupiedJobsDemand = unoccupiedJobsDemand;
	}
	

}
