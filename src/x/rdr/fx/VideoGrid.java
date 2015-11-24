package x.rdr.fx;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.image.Image;

public class VideoGrid {
	
	private ArrayList<ImageView> thumbs = new ArrayList<>();
	private ArrayList<Image> imgs = new ArrayList<>();
	private FlowPane grid = new FlowPane();
	
	public VideoGrid() {
		grid.setVgap(0);
		grid.setHgap(0);
		grid.setPrefWrapLength(1280);

		for(int i = 0; i < thumbs.size(); i++) {
			grid.getChildren().add(thumbs.get(i));
		}
	}
	
	public Node getNode() {
		return this.grid;
	}
	
	public void addImage(ArrayList<Image> imgArray, ArrayList<ImageView> imgViewArray) {
		for(int i = 0; i < imgViewArray.size(); i++) {
			imgViewArray.get(i).setImage(imgArray.get(i));
		}
	}
	

}
