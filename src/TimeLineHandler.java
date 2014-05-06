import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;


public class TimeLineHandler {
	public TimeLineHandler() {
	}
	
	Timeline cityTick;
	public void startCityTimeLine(final City city) {
		cityTick = new Timeline(new KeyFrame(Duration.seconds(15), new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent event) {
		        city.tick();
		    }
		}));
		cityTick.setCycleCount(Timeline.INDEFINITE);
		cityTick.play();
		
	}
	
}
