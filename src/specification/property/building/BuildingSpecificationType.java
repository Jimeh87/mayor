package specification.property.building;
import specification.SpecificationType;
import specification.property.building.concrete.BasicHomeSpecification;
import specification.property.building.concrete.FarmSpecification;
import specification.property.building.concrete.GrainProcessingPlantSpecification;
import specification.property.building.concrete.GroceryStoreSpecification;

public enum BuildingSpecificationType implements SpecificationType {
	BASIC_HOME(BasicHomeSpecification.class),
	FARM(FarmSpecification.class),
	GRAIN_PROCESSING_PLANT(GrainProcessingPlantSpecification.class),
	GROCERY_STORE(GroceryStoreSpecification.class);
	
	private BuildingSpecificationType(Class<?> specificationClass) {
		setSpecificationClass(specificationClass);
	}
	
	Class<?> specificationClass;
	public Class<?> getSpecificationClass() {
		return specificationClass;
	}
	public void setSpecificationClass(Class<?> specificationClass) {
		this.specificationClass = specificationClass;
	}
}