package x.rdr.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FlowFX extends Application {
	
//	private TitleBar titleBar = new TitleBar();
	private BorderPane root = new BorderPane();
	private VideoGrid grid = new VideoGrid();
	

	@Override
	public void start(Stage window) {
		window.initStyle(StageStyle.UNDECORATED);
		
		root.setStyle("-fx-background-color: #252629;");
//		root.setTop(titleBar.getNode());
		root.getChildren().addAll(grid.getNode());
		
		Scene mainScene = new Scene(root, 1280, 720);
		
		window.setScene(mainScene);
		window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
