package x.rdr.fx;



import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FlowFX extends Application {
	
//	private TitleBar titleBar = new TitleBar();
	private BorderPane root = new BorderPane();
	

	@Override
	public void start(Stage window) {
		
		VideoGrid videos = new VideoGrid();
		Scene mainScene = new Scene(root, 1280, 720);
		
		root.setStyle("-fx-background-color: #252629;");
		root.setCenter(videos.getNode());
		
		window.initStyle(StageStyle.UNDECORATED);
		window.setScene(mainScene);
		window.show();
		
		Input.keyboard(mainScene, videos);
	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
