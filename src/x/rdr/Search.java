
package x.rdr;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

/**
 * Print a list of videos matching a search term.
 *
 * @author Jeremy Walker
 */
public class Search {

    /**
     * Define a global variable that identifies the name of a file that
     * contains the developer's API key.
     */
    private static final String PROPERTIES_FILENAME = "youtube.properties";
	private static final String QUERY = "Worlds 2015";
	private static final long VIDEONUMBER = 30;
	
	private static SearchListResponse searchResponse;
	private static YouTube.Search.List search;
	
	private static List<Thumbnail> thumbnails = new ArrayList<>();
	private static List<String> ids = new ArrayList<>(); 
	
	private static int i = 0;
	private static int page = 0;
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
    public Search() {
    	
        page++;
        
        // Read the developer key from the properties file.
        Properties properties = new Properties();
        try {
            InputStream prop = this.getClass().getClassLoader().getResourceAsStream("info/" + PROPERTIES_FILENAME);
            properties.load(prop);

        } catch (IOException e) {
            System.err.println("Error! Couldn't read '" + PROPERTIES_FILENAME 
            		+ ". Reason: " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }

        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
        	if(page == 1) {
	            youtube = new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), new HttpRequestInitializer() {
	                public void initialize(HttpRequest request) throws IOException {
	                }
	            }).setApplicationName("youtube-cmdline-search-sample").build();
	
	            // Prompt the user to enter a query term.
	            // Define the API request for retrieving search results.
	            search = youtube.search().list("id,snippet");
	
	            // Set your developer key from the Google Developers Console for
	            // non-authenticated requests. See:
	            // https://console.developers.google.com/
	            String apiKey = properties.getProperty("apikey");
	            System.out.println(apiKey);
	            search.setKey(apiKey);
	            search.setQ(QUERY);
	
	            // Restrict the search results to only include videos. See:
	            // https://developers.google.com/youtube/v3/docs/search/list#type
	            search.setType("video");
	
	            // To increase efficiency, only retrieve the fields that the
	            // application uses.
	            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url),nextPageToken");
	            search.setMaxResults(VIDEONUMBER);
        	}
        	
            // Call the API and print results.
        	System.out.println(search.getPageToken());
            searchResponse = search.execute();
            
            List<SearchResult> searchResultList = searchResponse.getItems();
            
            // Fill list with thumbnails 
            for(int j = 0; j < searchResultList.size(); j++, i++) {
            	SearchResult video = searchResultList.get(j);
            	ids.add(i, video.getId().getVideoId());
            }
            
            search.setPageToken(searchResponse.getNextPageToken());
            
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    
    public Thumbnail getThumbnail(int index) {
    	return thumbnails.get(index);
    }
    public static String getId(int index) {
    	return ids.get(index);
    }
    public static long getVideoNumber() {
    	return VIDEONUMBER;
    }
    public static long getVideosLoaded() {
    	return (page * VIDEONUMBER);
    }
	public String getQuery() {
		return QUERY;
	}
}
