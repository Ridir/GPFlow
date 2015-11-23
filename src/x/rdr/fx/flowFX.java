package x.rdr.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class flowFX extends Application {

	@Override
	public void start(Stage window) {
		window.initStyle(StageStyle.UNDECORATED);
		
		HBox topBox = new HBox();
		topBox.getChildren().add(new Button());
//		topBox.setMinWidth(100);
//		topBox.setMinHeight(100);
		topBox.setStyle("-fx-background-color: #0000ff;");;
		
		BorderPane root = new BorderPane();
		
		root.getChildren().addAll(topBox);	
		Scene mainScene = new Scene(root, 1280, 720);
		
		window.setScene(mainScene);
		window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
