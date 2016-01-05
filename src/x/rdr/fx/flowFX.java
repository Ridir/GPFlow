package x.rdr.fx;



import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import x.rdr.VideoPlayer;

public class FlowFX extends Application {
	
//	private TitleBar titleBar = new TitleBar();
	private BorderPane root = new BorderPane();
	private BorderPane view = new BorderPane();

	@Override
	public void start(Stage window) {
		
		VideoPlayer.make();
		VideoPlayer.load("OBlgSz8sSM");
		VideoGrid videos = new VideoGrid();
		Scene mainScene = new Scene(root, 1280, 720);
		Scene videoScene = new Scene(view, 1280, 720);
		
		root.setStyle("-fx-background-color: #252629;");
		root.setCenter(videos.getNode());
		
		view.setStyle("-fx-background-color: #252629;");
		view.setCenter(VideoPlayer.getNode());
		
		window.initStyle(StageStyle.UNDECORATED);
		window.setScene(videoScene);
		window.show();
		
		Input.keyboard(mainScene, videos);
	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
