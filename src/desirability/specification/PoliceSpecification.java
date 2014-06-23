package desirability.specification;


public class PoliceSpecification extends DesirabilitySpecification {

	public PoliceSpecification() {
		super(5, 6);
	}
	
	@Override
	public int getDesirability() {
		//y = x^2
		//x = sqrt(y)
		//return getUnmodifiedDesirability() - (int)Math.sqrt((double)year);
		return getUnmodifiedDesirability();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	

}
