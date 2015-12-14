package x.rdr.fx;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Input {
	
	private static int x = 0;
	private static int y = 0;
	
	public static void keyboard(Scene scene, VideoGrid grid) {
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
		      
			if(key.getCode()==KeyCode.ENTER) {
		        System.out.println("You pressed enter");
		      }
			
			if(key.getCode()==KeyCode.W) {
		    	y--;
		    	y = Math.max(y, 0);
		    	grid.highlight(grid, x, y);
		      }
			
			if(key.getCode()==KeyCode.A) {
				x--;
				x = Math.max(x, 0);
		    	grid.highlight(grid, x, y);
		      }
		      
		    if(key.getCode()==KeyCode.S) {
		    	y++;
		    	grid.highlight(grid, x, y);
		      }
		      
		    if(key.getCode()==KeyCode.D) {
		    	x++;
				x = Math.min(x, 4);
		    	grid.highlight(grid, x, y);
		      }
		      
		});
	}
	
	
	public static void gamepad(Scene scene) {
		
	}

}