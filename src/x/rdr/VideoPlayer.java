package x.rdr;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class VideoPlayer {
	
	private static String url;
	private static WebView playBack;
	private static WebEngine playBackContent;

	public static void make() {
		playBack = new WebView();
		playBackContent = playBack.getEngine();
		config();
	}

	public static void load(String videoID) {
		try {
			
			Process youtubeDownload = Runtime.getRuntime().exec("python D:/Libraries/Documents/Workspaces/GPFlow/lib/youtube_dl/__main__.py -g https://www.youtube.com/watch?v=_" + videoID);
			url = new BufferedReader(new InputStreamReader(youtubeDownload.getInputStream())).readLine();
			System.out.println("Url: " + url);	
			playBackContent.load(url);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println("Error executing youtube-dl.py!");
			return;
			
		}
		
	}
	
	public static void config() {
		playBack.setPrefHeight(720);
		playBack.setPrefWidth(1280);
	}
	
	public static WebView getWebView() {
		return playBack;
	}
	
	public static Node getNode() {
		return playBack;
	}
	
}