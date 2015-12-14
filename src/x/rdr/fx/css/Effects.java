package x.rdr.fx.css;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;

public class Effects {
	
	int j;

	public static void brighten(ImageView img, double d) {
		
		ColorAdjust recolor = new ColorAdjust();
		
		recolor.setBrightness(d);
		
		img.setEffect(recolor);
	}
	
	public static void reset(ImageView img) {
		img.setEffect(null);
	}
}
