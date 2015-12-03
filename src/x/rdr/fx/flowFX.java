package x.rdr.fx;



import java.io.IOException;
import java.util.Scanner;

import javax.swing.text.Highlighter.Highlight;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FlowFX extends Application {
	
//	private TitleBar titleBar = new TitleBar();
	private BorderPane root = new BorderPane();
	private static Scanner keyboard = new Scanner(System.in);
	

	@Override
	public void start(Stage window) {
		window.initStyle(StageStyle.UNDECORATED);
		
		VideoGrid videos = new VideoGrid();
		
		root.setStyle("-fx-background-color: #252629;");
		root.setCenter(videos.getNode());
//		root.getChildren().addAll(videos.getNode());
		
		Scene mainScene = new Scene(root, 1280, 720);
		
		
		window.setScene(mainScene);
		window.show();
		
		System.out.println("lolololololololol");
		
	}

	public static void main(String[] args) throws IOException {
		launch(args);
		for(int i = 0; i <= 0; ) {
			String input = "";
			System.out.println("Meme");
			input = keyboard.next();
			System.out.println(input);
			
			VideoGrid.highlight(VideoGrid.thumbs.get(i));
			
		}
	}
}
