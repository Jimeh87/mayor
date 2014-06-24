package specification.desirability;

import specification.Specification;

public abstract class DesirabilitySpecification extends Specification {
	private int unmodifiedDesirability = 0;
	private int splashRadius = 0;
	public DesirabilitySpecification(int unmodifiedDesirability, int splashRadius) {
		this.unmodifiedDesirability = unmodifiedDesirability;
		this.splashRadius = splashRadius;
	}
	public int getUnmodifiedDesirability() {
		return unmodifiedDesirability;
	}
	protected void setUnmodifiedDesirability(int unmodifiedDesirability) {
		this.unmodifiedDesirability = unmodifiedDesirability;
	}
	
	public int getSplashRadius() {
		return splashRadius;
	}
	protected void setSplashRadius(int splashRadius) {
		this.splashRadius = splashRadius;
	}
	
	public abstract int getDesirability();
	public abstract void remove();
}
