package desirability.specification;

import objects.Specification;

public abstract class DesirabilitySpecification extends Specification {
	private int unmodifiedDesirability = 0;
	public DesirabilitySpecification(int unmodifiedDesirability) {
		this.unmodifiedDesirability = unmodifiedDesirability;
	}
	public int getUnmodifiedDesirability() {
		return unmodifiedDesirability;
	}
	protected void setUnmodifiedDesirability(int unmodifiedDesirability) {
		this.unmodifiedDesirability = unmodifiedDesirability;
	}
	
	public abstract int getDesirability(int year);
	public abstract void remove();
}
