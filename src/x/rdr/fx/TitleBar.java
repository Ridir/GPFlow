package x.rdr.fx;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class TitleBar {
	
	HBox titleBar = new HBox();
	
	public TitleBar() {
		

		titleBar.setPrefHeight(50);
		titleBar.setStyle("-fx-background-color: #252629;");
		
	}

	public Node getNode() {
		return this.titleBar;
	}

}
