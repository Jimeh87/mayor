package grid;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import specification.Specification;
import specification.SpecificationEntity;

public class GridIterator<T extends Specification> implements Iterator<SpecificationEntity<T>> {
	  
	private int index = 0;
	private Grid<T> grid;
	
	public GridIterator(Grid<T> grid) {
	    this.grid = grid;
	}
	 
	public boolean hasNext() {
	    return (index < (grid.getXSize() * grid.getYSize()));
	}
	 
	public SpecificationEntity<T> next() {
		return getNext();
	}
	 
	private SpecificationEntity<T> getNext() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		
		index++;
		SpecificationEntity<T> next = grid.getSpecificationEntity(getX(), getY());
		return next;

	}
	
	public int getY() {
		if (index == 0) {
			throw new NoSuchElementException();
		}
		
		int y = (index - 1) / grid.getXSize();
		System.out.println("  Y: " + y);
		return y;
	}
	
	public int getX() {
		if (index == 0) {
			throw new NoSuchElementException();
		}
		
		int x = (index - 1) % grid.getXSize();
		System.out.println("  X: " + x);
		return x;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public void forEachRemaining(Consumer action) {
		throw new UnsupportedOperationException();
	}
}
