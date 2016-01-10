package x.rdr.fx;



import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import x.rdr.Input;
import x.rdr.fx.css.Effects;

public class FlowFX extends Application {
	
	//	All objects later used are created as static here for future reference.
	private static BorderPane root = new BorderPane();
	private static BorderPane view = new BorderPane();
	private static Scene mainScene = new Scene(root, 1280, 720);
	private static Scene videoScene = new Scene(view, 1280, 720);
	private static Stage window;

	@Override
	public void start(Stage window) {
		
		//	Saves whatever window is entered to the static window variable
		FlowFX.window = window;
		
		//	Creates a grid containing thumbnails.
		VideoGrid videos = new VideoGrid();
		
		//	Sets the background color and fills each scene with fitting content.
		Effects.darken(root);
		Effects.darken(view);
		root.setCenter(videos.getNode());
		view.setCenter(VideoPlayer.getNode());
		
		//	Removes any bars from the window.
		window.initStyle(StageStyle.UNDECORATED);
		
		//	Sets the main "grid" scene as the first scene and makes it visible.
		window.setScene(mainScene);
		window.show();
		
		//	Checks keyboard for input in both scenes
		Input.keyboard(mainScene, videos);
		Input.keyboardVideo(videoScene, videos);
		//	TODO: Controller input
		
	}
	
	//	Changes the scene of window to whatever scene is given.
	public static void changeScene(Scene scene) {
		view.setCenter(VideoPlayer.getNode());
		window.setScene(scene);
	}
	
	//	Returns whatever scene is currently being displayed.
	public static Scene getActiveScene() {
		return window.getScene();
	}
	
	//	Returns videoScene.
	public static Scene getVideoScene() {
		return videoScene;
	}
	
	//	Returns mainScene.
	public static Scene getMainScene() {
		return mainScene;
	}
	
	public static BorderPane getRoot() {
		return root;
	}
	
	//	From here the application boots.
	public static void main(String[] args) throws IOException {
		launch(args);
	}
}