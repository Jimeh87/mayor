package objects;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StatWindowSceneBuilder {

	//Define
	private String popupDetails;
	private Stage statWindowStage;
	private double xOffset = 0;
	private double yOffset = 0;
	
	//Construct
	public StatWindowSceneBuilder(String popupDetails, Stage statWindowStage) {
		this.popupDetails = popupDetails;
		this.statWindowStage = statWindowStage;
		
	}
	//Parent Node Generation and Gather Children
	Parent generateStatWindow(){
		//Generate Main GridPane //TODO Look into scroll panes for mouse edge scroll events
		GridPane gridPane = new GridPane();
		gridPane.maxHeight(300); gridPane.maxWidth(300);
		gridPane.setId("MainWindow");
		
		//Generate and fill textBlock gridPane add Children
		Text popupDetailsText = new Text(popupDetails);
		gridPane.add(popupDetailsText, 0, 0);
		
		//Mouse Onpress and OnDragged events for moveable undecorated Stage
		gridPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        gridPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                statWindowStage.setX(event.getScreenX() - xOffset);
                statWindowStage.setY(event.getScreenY() - yOffset);
            }
        });
        //Return Node as Built Scene
		return gridPane;
	}
}
