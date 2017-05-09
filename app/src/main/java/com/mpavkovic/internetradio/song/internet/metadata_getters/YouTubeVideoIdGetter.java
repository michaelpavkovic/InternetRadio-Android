package com.mpavkovic.internetradio.song.internet.metadata_getters;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.mpavkovic.internetradio.utils.Constants;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Runs on a separate thread, obtains video id.
 */
public class YouTubeVideoIdGetter implements Runnable
{
    YouTube youtube;
    YouTube.Search.List search;
    boolean done = false;
    String artist, title, videoId = "";

    public YouTubeVideoIdGetter(String artist, String title)
    {
        this.artist = artist;
        this.title = title;
    }
    public void run()
    {
        //Initialize youtube object
        youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException
            {
            }
        }).setApplicationName("internet-radio").build();

        //Initialize search
        try
        {
            search = youtube.search().list("id");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        search.setKey(Constants.YOUTUBE_API_KEY_BROWSER);
        search.setType("video");
        search.setFields("items(id/videoId)");
        search.setMaxResults(1L);

        String queryTerm;
        StringBuilder queryBuilder = new StringBuilder();

        //Prepare query term
        queryBuilder.append(artist);
        queryBuilder.append(' ');
        queryBuilder.append(title);
        queryTerm = queryBuilder.toString();

        search.setQ(queryTerm);

        //Search YouTube using the API to find the topmost video
        SearchListResponse searchResponse = null;
        try
        {
            searchResponse = search.execute();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        List<SearchResult> searchResultList = searchResponse.getItems();

        //Go through the results and find the video ID
        Iterator<SearchResult> iteratorSearchResults = searchResultList.iterator();

        while (iteratorSearchResults.hasNext())
        {
            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            videoId = rId.getVideoId();
        }

        System.out.println("VIDEO ID   " + videoId);

        done = true;
    }

    public void waitUntilDone()
    {
        for (int i = 0; i < 100; i++)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            if (done) break;
        }
    }

    public String getVideoId()
    {
        return videoId;
    }
}
