import java.util.ArrayList;
import java.util.List;

import objects.Grid;
import objects.Tile;



public class Main {

	private static boolean running;
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
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			List<Tile> tilesToUpdate;
			if (delta >= 1) {
				tilesToUpdate = tick();
				delta--;
			}
			render();
		}
	}

	private static ArrayList<Tile> tick() {
		city.processTick();
		
		return null;
		
	}

	private static void render() {
		
	}

	private static void initializeGame(String[] args) {
		InitGame.launchApplication(args);
	}
	
	
	

}
