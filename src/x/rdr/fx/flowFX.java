package x.rdr.fx;



import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import x.rdr.Input;

public class FlowFX extends Application {
	
//	private TitleBar titleBar = new TitleBar();
	private static BorderPane root = new BorderPane();
	private static BorderPane view = new BorderPane();
	private static Scene mainScene = new Scene(root, 1280, 720);
	private static Scene videoScene = new Scene(view, 1280, 720);
	private static Stage window;

	@Override
	public void start(Stage window) {
		FlowFX.window = window;
		
//		VideoPlayer.make();
//		VideoPlayer.load("dFdljB_Bcx0");
		VideoGrid videos = new VideoGrid();
		
		root.setStyle("-fx-background-color: #252629;");
		root.setCenter(videos.getNode());
		
		view.setStyle("-fx-background-color: #252629;");
		view.setCenter(VideoPlayer.getNode());
		
		window.initStyle(StageStyle.UNDECORATED);
		window.setScene(mainScene);
		window.show();
		
		Input.keyboard(mainScene, videos);
		Input.keyboard(videoScene, videos);
	}
	
	public static void changeScene(Scene scene) {
		view.setCenter(VideoPlayer.getNode());
		window.setScene(scene);
	}
	
	public static Scene getActiveScene() {
		return window.getScene();
	}
	
	public static Scene getVideoScene() {
		return videoScene;
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
