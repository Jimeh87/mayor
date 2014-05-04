import java.util.List;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import objects.Tile;



public class Main extends Application {

	private static boolean running = true;
	private static City city = new City(45, 28);

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
			
			List<Tile> tilesToUpdate = null;
			if (delta >= 1) {
				tilesToUpdate = tick();
				delta--;
			}
//			render(tilesToUpdate);
			running = false;
		}
	}

	private static List<Tile> tick() {
		return city.processTick();
	}

//	private static void render(List<Tile> tilesToUpdate) {
//		for (Tile tile : tilesToUpdate) {
//			
//		}
//	}

	private static void initializeGame(String[] args) {
		launchApplication(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {  
			
			//Main Scene Build
			Scene scene = new Scene(createGridPane(), 1920, 1080);
			scene.getStylesheets().add("/resources/graphics/gameGridStyle.css");
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace(); //Error Exception Catch
		}
	
	}
	
	public static void launchApplication(String[] args) {
		Application.launch(args);
	}
	
	// Grid Pane Constructor and Grid Constraints UI Spacing
	Parent createGridPane() {
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(1);gridPane.setVgap(1);
		
		Pane pnLeftGap = new Pane();
		pnLeftGap.setMinSize(240, 135);
		pnLeftGap.setMaxSize(240, 135);
		pnLeftGap.setId("topLeftBar");
		gridPane.add(pnLeftGap, 0, 0);
		
		Pane pnTopGap = new Pane();
		pnTopGap.setMinSize(1440, 135);
		pnTopGap.setMaxSize(1440, 135);
		pnTopGap.setId("topBar");
		gridPane.add(pnTopGap, 1, 0);
		
		GridPane gpGameGrid = new GridPane();
		for (int gridY = 0; gridY < 28; gridY++) {
			for (int gridX = 0; gridX < 45; gridX++) {
				Pane pnGameGrid = new Pane();
				pnGameGrid.setMinSize(32, 32);
				pnGameGrid.setMaxSize(32,  32);
				Tile tile = city.getGrid().getTile(gridX, gridY);
				pnGameGrid.setId(tile.getTileType().getId());
				gpGameGrid.add(pnGameGrid, gridX, gridY);
			}
			 
		}
		gridPane.add(gpGameGrid, 1, 1);
		
		
		
		return gridPane;
	}
	

}
