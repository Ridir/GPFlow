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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import x.rdr.Search;

public class VideoGrid {
	
	public static ArrayList<ImageView> thumbs = new ArrayList<>();
	private ArrayList<Image> imgs = new ArrayList<>();
	private FlowPane grid = new FlowPane();
	private ScrollPane sp = new ScrollPane();
	private ArrayList<Image> thumbArray = new ArrayList<>();
	
	private int THUMBNAIL_WIDTH = 240;
	private int THUMBNAIL_HEIGHT = 135;
	
	

	public VideoGrid() {
		
		// Apply configuration
		config();
		
		// Declare search
		Search search = new Search("Yogscast", 30);
		
		for(int i = 0; i < search.getNumVids(); i++) {
			URL url;
			BufferedImage bImg = null;
			try {
				url = new URL("http://i1.ytimg.com/vi/" + search.getId(i) + "/0.jpg");
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
		this.addImages(thumbArray);
		
		System.out.println("2. " + thumbs.size());
		for(int i = 0; i < thumbs.size(); i++) {
			grid.getChildren().add(thumbs.get(i));

		}
		
		sp.setContent(grid);
		
		
		
	}
	
	public Node getNode() {
		return sp;
	}
	
	private void addImages(ArrayList<Image> imgArray) {
		
		for(int i = 0; i < imgArray.size(); i++) {
			ImageView j = new ImageView();
			j.setFitWidth(THUMBNAIL_WIDTH);
			j.setFitHeight(THUMBNAIL_HEIGHT);
			thumbs.add(i, j);
			thumbs.get(i).setImage(imgArray.get(i));
		}
		System.out.println("1. " + thumbs.size());
		
	}
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
	
	public static void highlight(Node imgNode) {
		DropShadow highlight = new DropShadow();
		highlight.setColor(Color.web("#FFFFFF"));
		highlight.setOffsetX(0f);
		highlight.setOffsetY(0f);
		highlight.setHeight(20);
		imgNode.setEffect(highlight);
	
	}
	
}
