import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import objects.Tile;


public class SceneBuilder {
	// Grid Pane Constructor and Grid Constraints UI Spacing
	// Returns GridPane to main scene Uses City for ini info
	// Style info set by gameGridStyle.css in resources.graphics
		Parent generateGridPane(City city) {
			
			GridPane gridPane = new GridPane();
			gridPane.setHgap(1);gridPane.setVgap(1);
			
			//Top Gap Spacers Used for Whatever
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
			
			//Main Game GridPane set from City
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
			
			 
			//Control GridPane 
			GridPane gpControlGrid = new GridPane();
			gpControlGrid.setId("controlGrid");
			gpControlGrid.setMinSize(235, 540);
			gridPane.add(gpControlGrid, 2, 1);
			
			//Zone Residential Button
			Label resBtn = new Label();
			resBtn.setId("resButton");
			resBtn.setMinSize(235,30);
			resBtn.setMaxSize(235,30);
			gpControlGrid.add(resBtn, 0,0);
			
			//Zone Commercial Button
			Label commBtn = new Label();
			commBtn.setId("commButton");
			commBtn.setMinSize(235,30);
			commBtn.setMaxSize(235,30);
			commBtn.setText("Zone Commercial");
			gpControlGrid.add(commBtn, 0, 1);
			
			return gridPane;
		}
}
