package com.mpavkovic.internetradio.song;

import com.mpavkovic.internetradio.song.internet.PlaylistGetter;
import com.mpavkovic.internetradio.song.internet.metadata_getters.YouTubeVideoIdGetter;
import com.mpavkovic.internetradio.utils.Constants;
import com.mpavkovic.internetradio.utils.Genres.genres;

/**
 * Class that gets a playlist of songs from SirusXm to help with better song predictions
 */
public class SiriusXmPlaylistGetter
{
    PlaylistGetter getter;
    SongChecker checker;
    boolean done = false;

    public SiriusXmPlaylistGetter(genres genre)
    {
        checker = new SongChecker();

        //Initialize getter
        getter = new PlaylistGetter(genre);
        Thread getterThread = new Thread(getter);
        getterThread.start();
        getter.waitUntilDone();
    }

    public Song[] createXmPlaylist()
    {
        String temp;

        Song[] songs = new Song[Constants.DEFAULT_PLAYLIST_LENGTH];

        temp = getter.website.substring(getter.website.indexOf("Artist") + 6);
        temp = temp.substring(temp.indexOf("Artist") + 6);

        String artist, title;
        //Loop through lines of HTML parsing out titles and artists
        for (int i = 0; i < Constants.DEFAULT_PLAYLIST_LENGTH; i++)
        {
            temp = temp.substring(temp.indexOf("</td><td>") + 9);
            artist = temp.substring(0, temp.indexOf("</td><td>"));

            System.out.println("Artist: " + artist + '\n');

            temp = temp.substring(temp.indexOf("</td><td>") + 9);
            title = temp.substring(0, temp.indexOf("</td><td>"));

            System.out.println("Title: " + title + '\n');

            temp = temp.substring(temp.indexOf("</td><td>") + 9);
            temp = temp.substring(temp.indexOf("</td><td>") + 9);

            if (checker.isSong(artist, title))
            {
                songs[i] = new Song();
                songs[i].setSongArtist(artist);
                songs[i].setSongName(title);

                //Initialize getter for video id and update ids
                YouTubeVideoIdGetter youTubeVideoIdGetter = new YouTubeVideoIdGetter(songs[i].getSongArtist(), songs[i].getSongName());
                Thread getterThread = new Thread(youTubeVideoIdGetter);
                getterThread.start();
                youTubeVideoIdGetter.waitUntilDone();
                songs[i].setSongYouTubeVideoId(youTubeVideoIdGetter.getVideoId());

                //Update genre
                songs[i].setSongGenre("Deep House/Chill");
            }
        }

        done = true;
        return songs;
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
