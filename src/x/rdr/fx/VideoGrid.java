package x.rdr.fx;



import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import x.rdr.Search;
import x.rdr.fx.css.Effects;

public class VideoGrid {
	
	public 	ArrayList<ImageView> thumbs = new ArrayList<>();
	private FlowPane grid = new FlowPane();
	private ScrollPane sp = new ScrollPane();
	private ArrayList<Image> thumbArray = new ArrayList<>();
	private URL url;
	private BufferedImage bImg = null;
	
	private int THUMBNAIL_WIDTH = 240;
	private int THUMBNAIL_HEIGHT = 135;
	int j;
	
	

	public VideoGrid() {
		
		// Apply configuration
		config();
		
		// TODO
		load(new Search("Worlds 2015"));
		
		// Highlight() workaround
		int i = this.getNumberInLine(0, 0);
		Effects.brighten(this.thumbs.get(i), 0.2);
		
	}
	
	
	

	
/*	This function isn't optimal although difficult to improve
  	Just leave it be and let it do its thing.
  	It loads more videos from a search.	*/
	private void load(Search search) {
		
		int d = (int) (Search.getVideosLoaded() - Search.getVideoNumber());
		System.out.println("the d: " + d);
		
		for(int i = d; i < Search.getVideosLoaded(); i++) {
			
			try {
				
				url = new URL("http://i1.ytimg.com/vi/" + Search.getId(i) + "/0.jpg");
				bImg = ImageIO.read(url).getSubimage(0, 45, 480, 270);
				
			} catch (MalformedURLException e) {
				
				System.err.println("Broken URL!");
				e.printStackTrace();
				break;
				
			} catch (IOException e) {
				
				System.err.println("IOException! Ouch!");
				e.printStackTrace();
				break;
				
			}
			
			Image img = SwingFXUtils.toFXImage(bImg, null);
			thumbArray.add(img);
		}
		
		this.addImages(thumbArray, thumbs);
		
		System.out.println("2. " + thumbs.size());
		
		for(int i = d; i < thumbs.size(); i++) {
			System.out.println(i);
			grid.getChildren().add(thumbs.get(i));
		}
		
		sp.setContent(grid);
	}
	
	//	Puts the css highlight effect upon the thumbnail at given x-y coordinate and removes it from the previous.
	public void highlight(VideoGrid grid, int x, int y) {
		
		int i = grid.getNumberInLine(x, y);
		try {
			Effects.reset(thumbs.get(j));
			Effects.brighten(grid.thumbs.get(i), 0.2);
		} 
		catch(IndexOutOfBoundsException oob) {
			//	TODO
			this.load(new Search("Worlds 2015"));
		}
		
		j = i;
		
	}
	
	//	Adds each image from an array of images to its own imageview and inserts that into an array of imageviews.
	private void addImages(ArrayList<Image> imgArray, ArrayList<ImageView> imgViewArray) {
		
		for(int i = (int) (Search.getVideosLoaded() - Search.getVideoNumber()); i < imgArray.size(); i++) {
			
			ImageView j = new ImageView();
			j.setFitWidth(THUMBNAIL_WIDTH);
			j.setFitHeight(THUMBNAIL_HEIGHT);
			imgViewArray.add(i, j);
			imgViewArray.get(i).setImage(imgArray.get(i));
			
		}
		
	}
	
	//	Configures all objects that need its style changed for whatever reason.
	private void config() {
		sp.setPadding(new Insets(0, 0, 0, 80));
		sp.setPrefWidth(1200);
		sp.setPrefHeight(720);
		sp.setStyle("-fx-background-color:transparent;");
		sp.setFitToWidth(true);
		sp.setVbarPolicy(ScrollBarPolicy.NEVER);
		grid.setColumnHalignment(HPos.CENTER);
		grid.setVgap(0);
		grid.setHgap(0);
		grid.setPrefWrapLength(720);
		grid.setPrefWidth(720);
	}
	
	//	Converts coordinates to index of array.
	private int getNumberInLine(int x, int y) {
		int ans = 5 * y + x;
		return ans; 
	}
	
	//	Loads a video at given coord.
	public void playVideoFromCoord(int x, int y) {
		int numberInLine = getNumberInLine(x, y);
		VideoPlayer.load(Search.getId(numberInLine));
	}
	
	//	Returns the node for the scrollable grid of videos.
	public Node getNode() {
		return sp;
	}
	
}