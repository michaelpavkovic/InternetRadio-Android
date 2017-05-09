package com.mpavkovic.internetradio.song.internet;

import com.mpavkovic.internetradio.activities.nowplaying.Radio;
import com.mpavkovic.internetradio.utils.GenreUtils;
import com.mpavkovic.internetradio.utils.Genres.genres;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Retrieves DogStarRadio webpage on separate thread
 */
public class PlaylistGetter implements Runnable
{
    int siriusXmChannel;
    GenreUtils genreUtils;

    HttpClient client;
    HttpGet request;
    HttpResponse response;

    InputStream in;
    BufferedReader br;
    StringBuilder sb;

    String url;
    public String website;
    boolean done = false;

    //Constructor
    public PlaylistGetter(genres genre)
    {
        genreUtils = new GenreUtils();

        //Get channel id to create xm playlist out of
        siriusXmChannel = genreUtils.getSiriusXmChannelNumber(Radio.selectedGenre);
    }

    @Override
    public void run()
    {
        try
        {
            //Prepare URL
            StringBuilder urlBuilder = new StringBuilder("");
            urlBuilder.append("http://www.dogstarradio.com/search_xm_playlist.php?artist=&title=&channel=");
            urlBuilder.append(siriusXmChannel);
            urlBuilder.append("&month=&date=&shour=&sampm=&stz=&ehour=&eampm=");

            url = urlBuilder.toString();

            client = new DefaultHttpClient();
            request = new HttpGet(url);
            response = client.execute(request);

            in = response.getEntity().getContent();
            br = new BufferedReader(new InputStreamReader(in));
            sb = new StringBuilder();

            String line;

            //Read the website's HTML
            while ((line = br.readLine()) != null)
            {
                sb.append(line + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        website = sb.toString();

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
}
