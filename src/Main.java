import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
			render(tilesToUpdate);
			running = false;
		}
	}

	private static List<Tile> tick() {
		return city.processTick();
	}

	private static void render(List<Tile> tilesToUpdate) {
		for (Tile tile : tilesToUpdate) {
			
		}
	}

	private static void initializeGame(String[] args) {
		launchApplication(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {  
			
			//Main Scene Build
			SceneBuilder sceneBuilder = new SceneBuilder();
			Scene scene = new Scene(sceneBuilder.generateGridPane(city), 1920, 1080);
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
	

}
