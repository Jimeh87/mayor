package grid;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import specification.property.PropertySpecification;

public class GridTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIterator() {
		int x = 45;
		int y = 28;
		Grid<PropertySpecification> grid = new Grid<PropertySpecification>(x, y);
		GridIterator<PropertySpecification> i = grid.iterator();
		
		assertTrue(i.hasNext());

		for (int gridY = 0; gridY < y; gridY++) {
			for (int gridX = 0; gridX < x; gridX++) {
				i.next();
				assertEquals(gridX, i.getX());
				assertEquals(gridY, i.getY());
				if ((x - 1) * (y - 1) != gridX * gridY) {
					assertTrue(i.hasNext());
				}
			}
		}
		
		assertFalse(i.hasNext());
	}

}
