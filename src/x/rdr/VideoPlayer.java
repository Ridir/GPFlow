package x.rdr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.media.Media;

public class VideoPlayer {
	
	//	Replacement temporary id
	private static String v = "OBlgSz8sSM";
	private static Media playBack;
	
	
	public static void play(String videoID) {
		try {
			
			Runtime rt = Runtime.getRuntime();
			
			Process ydl = rt.exec("python D:/Dokument/Workspaces/GPFlow/lib/youtube_dl/__main__.py -g https://www.youtube.com/watch?v=_" + v);
			
			BufferedReader ydlOutput = new BufferedReader(new InputStreamReader(ydl.getInputStream()));
			
			String url = ydlOutput.readLine();
			
			System.out.println(url);
			playBack = new Media(url);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println("Error executing youtube-dl.py!");
			return;
			
		}
		
		
	}
	public static void main(String[] args) {
		play("");
	}
}
