package com.mpavkovic.internetradio.song.internet.metadata_getters;

import com.mpavkovic.internetradio.utils.Constants;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Class that contains methods that fetch metadata for songs
 */
public class MetadataGetter
{
    HttpClient client;
    HttpGet request;
    HttpResponse response;

    InputStream in;
    BufferedReader br;
    StringBuilder sb;

    String url, website;

    public MetadataGetter()
    {

    }

    public String getArtist()
    {
        String allMetadata, artist;

        allMetadata = parseHtml();

        sb = new StringBuilder();
        sb.append(allMetadata);

        artist = sb.substring(0, allMetadata.indexOf('-') - 1);

        return artist;
    }

    public String getTitle()
    {
        String allMetadata, title;

        allMetadata = parseHtml();

        sb = new StringBuilder();
        sb.append(allMetadata);

        title = sb.substring(allMetadata.indexOf('-') + 1, allMetadata.lastIndexOf('-') - 1);

        return title;
    }

    public void getGenre()
    {
        //TODO complete this method
    }

    private String parseHtml()
    {
        String allMetadata;

        sb = new StringBuilder();
        sb.append(website);

        allMetadata = sb.substring(website.indexOf(Constants.metadataStart));
        allMetadata = sb.substring(website.indexOf(Constants.metadataStart));
        allMetadata = sb.substring(website.indexOf(Constants.metadataStart, website.indexOf(Constants.metadataEnd)));

        System.out.println(allMetadata);

        return allMetadata;
    }
}
