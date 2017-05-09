package com.mpavkovic.internetradio.song.internet.metadata_getters;

import com.mpavkovic.internetradio.song.Song;
import com.mpavkovic.internetradio.utils.Constants;
import com.mpavkovic.internetradio.utils.UtfEncodingUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class that holds a method that retrieves the URL of the album art for a song
 */
public class AlbumArtUrlGetter
{
    public String getAlbumArtUrlFromSong(Song song) throws IOException
    {
        String output = "", titleInUrlForm, line, website;


        if (song.hasTitle())
        {
            titleInUrlForm = UtfEncodingUtil.encode(song.getSongName() + " " + song.getSongArtist(), Constants.utf8);

            String url = "http://www.albumart.org/index.php?searchk=" + titleInUrlForm + "&itempage=1&newsearch=1&searchindex=Music";

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            InputStream in = response.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();


            while ((line = br.readLine()) != null)
            {
                sb.append(line + "\n");
            }

            website = sb.toString();

            in.close();
            br.close();

            System.out.println(website);
        }
        else
        {
            titleInUrlForm = Constants.random_string_of_characters_that_gaurentee_no_music_results;
        }




        return output;
    }
}
