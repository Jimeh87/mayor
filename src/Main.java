import java.util.ArrayList;
import java.util.List;

import objects.Grid;
import objects.Residential;
import objects.Tile;



public class Main {

	private static boolean running = true;
	private static City city = new City(45, 45);

	public static void main(String[] args) {
		initializeGame(args);
		//run();
	}

	private static void run() {

		long lastTime = System.nanoTime();
		final double amountOfTicks = 60D;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		Grid screenGrid = new Grid(45, 45);
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			List<Tile> tilesToUpdate;
			if (delta >= 1) {
				tilesToUpdate = tick();
				delta--;
			}
			render(screenGrid);
			running = false;
		}
	}

	private static List<Tile> tick() {
		return city.processTick();
	}

	private static void render(Grid screenGrid) {
		for (int y = 0; y < screenGrid.getYSize(); y++) {
			for (int x = 0; x < screenGrid.getXSize(); x++) {
				System.out.print(screenGrid.getTile(x, y).getTileType().getId());
			}
			System.out.println();
		}
	}

	private static void initializeGame(String[] args) {
		InitGame.launchApplication(args);
	}
	
	
	

}
