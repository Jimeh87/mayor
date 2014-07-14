package economy;

import grid.Grid;

import org.junit.Before;
import org.junit.Test;

import specification.property.PropertySpecification;

public class SupplyAndDemandTest {

	SupplyAndDemand supplyAndDemand;
	@Before
	public void setUp() throws Exception {
		supplyAndDemand = new SupplyAndDemand(new Grid<PropertySpecification>(10, 10));
	}

	@Test
	public void testProductDemandSupplyRatioWithNoProduct() {
		Product p = Product.FOOD;
		System.out.println("-------------------------------------------");
		testDemandSupplyForProduct(p, 0, 0);
		testDemandSupplyForProduct(p, 10, 0);
		testDemandSupplyForProduct(p, 5, 10);
		testDemandSupplyForProduct(p, 10, 10);
		testDemandSupplyForProduct(p, 10, 5);
		testDemandSupplyForProduct(p, 0, 10);
		testDemandSupplyForProduct(p, 5, 4);
		testDemandSupplyForProduct(p, 0, 1);
		testDemandSupplyForProduct(p, 5, 100);
		testDemandSupplyForProduct(p, 100, 5);
		testDemandSupplyForProduct(p, 10000000, 1);
		System.out.println("-------------------------------------------");
		for (int s = 0; s < 100; s = s + 15) {
			for (int d = 0; d < 100; d = d + 15) {
				testDemandSupplyForProduct(p, s, d);
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("-------------------------------------------");
	}
	
	private void testDemandSupplyForProduct(Product product, Integer supply, Integer demand) {
		supplyAndDemand.getMaxSupply().setQuantityForProduct(product, supply);
		supplyAndDemand.getDemand().setQuantityForProduct(product, demand);
		System.out.println("product: " + product + " S: " + supply + " D: " + demand + " R: " + supplyAndDemand.getProductSupplyDemandRatio(product));
	}

}
