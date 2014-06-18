package desirability.specification;


public class PoliceSpecification extends DesirabilitySpecification {

	public PoliceSpecification(int unmodifiedDesirability) {
		super(unmodifiedDesirability);
	}
	
	@Override
	public int getDesirability(int year) {
		//y = x^2
		//x = sqrt(y)
		return getUnmodifiedDesirability() - (int)Math.sqrt((double)year);
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	

}
