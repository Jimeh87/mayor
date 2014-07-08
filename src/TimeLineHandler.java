import objects.City;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;


/**
 * Handles all TimeLines.
 * @author Jim
 */
public class TimeLineHandler {
	public TimeLineHandler() {
	}
	
	Timeline cityTick;
	/**
	 * Used as a tick for City
	 * @param city
	 */
	public void startCityTimeLine(final City city) {
		cityTick = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent event) {
		        city.tick();
		        System.out.println("Tick");
		    }
		}));
		cityTick.setCycleCount(Timeline.INDEFINITE);
		cityTick.play();
		
	}
	
}
