package x.rdr;
<<<<<<< HEAD
/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
=======

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
>>>>>>> origin/master
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
<<<<<<< HEAD
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
=======
>>>>>>> origin/master

/**
 * Print a list of videos matching a search term.
 *
 * @author Jeremy Walker
 */
<<<<<<< HEAD
public class Search{
=======
public class Search {
>>>>>>> origin/master

    /**
     * Define a global variable that identifies the name of a file that
     * contains the developer's API key.
     */
    private static final String PROPERTIES_FILENAME = "youtube.properties";
<<<<<<< HEAD

    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;

=======
    
	private static List<Thumbnail> thumbnails = new ArrayList<>();
	private static List<String> ids = new ArrayList<>(); 
	
	private static long numVids;
>>>>>>> origin/master
    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private static YouTube youtube;

    /**
     * Initialize a YouTube object to search for videos on YouTube. Then
     * display the name and thumbnail image of each video in the result set.
     *
     * @param args command line args.
     */
<<<<<<< HEAD
    public static void main(String[] args) {
        // Read the developer key from the properties file.
        Properties properties = new Properties();
        try {
            InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
=======
    public Search(String query, long numVids) {
    	this.numVids = numVids;
        // Read the developer key from the properties file.
        Properties properties = new Properties();
        try {
            InputStream prop = this.getClass().getClassLoader().getResourceAsStream("info/" + PROPERTIES_FILENAME);
            properties.load(prop);

        } catch (IOException e) {
            System.err.println("Error! Couldn't read '" + PROPERTIES_FILENAME + ". Reason: " + e.getCause()
>>>>>>> origin/master
                    + " : " + e.getMessage());
            System.exit(1);
        }

        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Prompt the user to enter a query term.
<<<<<<< HEAD
            String queryTerm = getInputQuery();
=======
>>>>>>> origin/master

            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");

            // Set your developer key from the Google Developers Console for
            // non-authenticated requests. See:
            // https://console.developers.google.com/
<<<<<<< HEAD
            String apiKey = properties.getProperty("youtube.apikey");
            search.setKey(apiKey);
            search.setQ(queryTerm);
=======
            String apiKey = properties.getProperty("apikey");
            System.out.println(apiKey);
            search.setKey(apiKey);
            search.setQ(query);
>>>>>>> origin/master

            // Restrict the search results to only include videos. See:
            // https://developers.google.com/youtube/v3/docs/search/list#type
            search.setType("video");

            // To increase efficiency, only retrieve the fields that the
            // application uses.
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
<<<<<<< HEAD
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
=======
            search.setMaxResults(numVids);
>>>>>>> origin/master

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
<<<<<<< HEAD
            if (searchResultList != null) {
                prettyPrint(searchResultList.iterator(), queryTerm);
            }
=======
            
            // Fill list with thumbnails 
            for(int i = 0; i < searchResultList.size(); i++) {
            	SearchResult video = searchResultList.get(i);
            	ids.add(i, video.getId().getVideoId());
//            	System.out.println(video);
//    	    	thumbnails.add(i, video.getSnippet().getThumbnails().getMaxres());
            }
            
>>>>>>> origin/master
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

<<<<<<< HEAD
    /*
     * Prompt the user to enter a query term and return the user-specified term.
     */
    private static String getInputQuery() throws IOException {

        String inputQuery = "";

        System.out.print("Please enter a search term: ");
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        inputQuery = bReader.readLine();

        if (inputQuery.length() < 1) {
            // Use the string "YouTube Developers Live" as a default.
            inputQuery = "YouTube Developers Live";
        }
        return inputQuery;
    }

    /*
     * Prints out all results in the Iterator. For each result, print the
     * title, video ID, and thumbnail.
     *
     * @param iteratorSearchResults Iterator of SearchResults to print
     *
     * @param query Search query (String)
     */
    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                System.out.println(" Video Id" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
=======
    
    public Thumbnail getThumbnail(int index) {
    	return thumbnails.get(index);
    }
    public String getId(int index) {
    	return ids.get(index);
    }
    public long getNumVids() {
    	return numVids;
>>>>>>> origin/master
    }
}