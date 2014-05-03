import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;



public class InitGame extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {  
			
			//Main Scene Build
			Scene scene = new Scene(createGridPane(), 1920, 1080);
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace(); //Error Exception Catch
		}
	
	}
	
	// Grid Pane Constructor and Grid Constraints UI Spacing
	private Parent createGridPane() {
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(16);gridPane.setVgap(16);
		
		
		Label lbTitle = new Label("Mayor");
		GridPane.setHalignment(lbTitle, HPos.CENTER);
		GridPane.setValignment(lbTitle, VPos.CENTER);
		gridPane.add(lbTitle, 1, 1);
		
		
		return gridPane;
		

	}
	public static void launchApplication(String[] args) {
		Application.launch(args);
	}

}
