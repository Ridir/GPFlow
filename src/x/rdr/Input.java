package x.rdr;



import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import x.rdr.fx.FlowFX;
import x.rdr.fx.SearchBox;
import x.rdr.fx.VideoGrid;

public class Input {
	
	//	Co-ordinates in VideoGrids
	private static int x = 0;
	private static int y = 0;
	
	//Keyboard input while browsing videos
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
		    
		    if(key.getCode()==KeyCode.K) {
		    	grid.playVideoFromCoord(x, y);
		    }
		    
		    if(key.getCode()==KeyCode.L) {
		    	FlowFX.getRoot().setTop(new SearchBox("Search", 1000));
		    	System.out.println("Made SearchBox");
		    }

		});
	}
	
	//	Keyboard input while playing video.
	public static void keyboardVideo(Scene scene, VideoGrid grid) {
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {

		});
	}
	
	//	Gamepad input while browsing videos.
	public static void gamepad(Scene scene) {
		//	TODO: Add gamepad controlls
	}
	
	//	Gamepad input while playing video.
	public static void gamepadVideo(Scene scene) {
		//	TODO: Add gamepad controlls
	}

}