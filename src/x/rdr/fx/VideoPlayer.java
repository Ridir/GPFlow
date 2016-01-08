package x.rdr.fx;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class VideoPlayer {
	
	private static String url;
	private static MediaView playbackView;
	private static MediaPlayer playback;

	public static void make() {

	}

	public static void load(String videoID) {
		//	The application will return an error message if the youtube-dl component fails.
		try {
			
			//	Extracts a direct video url with the ID as reference.
			Process youtubeDownload = Runtime.getRuntime().exec("python D:/Libraries/Documents/Workspaces/GPFlow/lib/youtube_dl/__main__.py -g -f best[height=720] https://www.youtube.com/watch?v=" + videoID);
			
			//	Saves the url as a String and prints it for debugging.
			url = new BufferedReader(new InputStreamReader(youtubeDownload.getInputStream())).readLine();
			System.out.println("Url: " + url);
			
			//	Creates a playback as well as a mediaplayer.
			playback = new MediaPlayer(new Media(url));
			playbackView = new MediaView(playback);
			config();
			
			//	Switch to video window
			FlowFX.changeScene(FlowFX.getVideoScene());
			
			//	Plays the mediafile.
			playback.play();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error executing youtube-dl command! Has the 'youtube-dl' package been installed correctly?");
			return;
			
		}
		
	}
	
	public static void config() {
		playbackView.fitWidthProperty().bind(Bindings.selectDouble(playbackView.sceneProperty(), "width"));
		playbackView.fitHeightProperty().bind(Bindings.selectDouble(playbackView.sceneProperty(), "height"));

		playbackView.setPreserveRatio(true);
	}
	
	public static Node getNode() {
		return playbackView;
	}
	
	public static void pause() {
		playback.pause();
	}
	
}