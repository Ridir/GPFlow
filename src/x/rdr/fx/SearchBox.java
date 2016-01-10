package x.rdr.fx;



import javafx.scene.control.TextField;

public class SearchBox extends TextField {
	
	public SearchBox(String text, int width) {
		
		super(text);
		
		setPrefWidth(width);
		
//		Effects.darken(this);
		
	}

}