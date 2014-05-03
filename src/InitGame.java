import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class InitGame extends Application {
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
		for (int gridX = 0; gridX < 28; gridX++) {
			for (int gridY = 0; gridY < 45; gridY++) {
				Pane pnGameGrid = new Pane();
				pnGameGrid.setMinSize(32, 32);
				pnGameGrid.setMaxSize(32,  32);
				pnGameGrid.setId("empty");
				gpGameGrid.add(pnGameGrid, gridY, gridX);
			}
			 
		}
		gridPane.add(gpGameGrid, 1, 1);
		
		
		
		return gridPane;
	}
	public static void launchApplication(String[] args) {
		Application.launch(args);
	}

}
