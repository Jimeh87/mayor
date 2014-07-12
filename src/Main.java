import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import objects.City;
import objects.Constants;
import objects.Cursor;

public class Main extends Application {

	final Cursor cursor = new Cursor();
	private static City city = new City(Constants.GRID_X, Constants.GRID_Y);
	private TimeLineHandler timeLineHandler = new TimeLineHandler();

	public static void main(String[] args) {
		launchApplication(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//Main Scene Build
			SceneBuilder sceneBuilder = new SceneBuilder();
			Scene scene = new Scene(sceneBuilder.generateGridPane(city, cursor), 1920, 1080);
			scene.getStylesheets().add("/resources/graphics/gameGridStyle.css");
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(false);
			primaryStage.setMaximized(true);
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
			timeLineHandler.startCityTimeLine(city);
		} catch(Exception e) {
			e.printStackTrace(); //Error Exception Catch
		}
	
	}
	
	/**
	 * Launches the main application
	 * @param args
	 */
	public static void launchApplication(String[] args) {
		Application.launch(args);
	}
	

}
