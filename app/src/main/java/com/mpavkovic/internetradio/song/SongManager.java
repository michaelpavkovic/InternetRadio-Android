package com.mpavkovic.internetradio.song;

import com.mpavkovic.internetradio.activities.nowplaying.Radio;

/**
 * Manager that holds the song playlist, and allows for use of the playlist
 */
public class SongManager
{
    Song[] currentPlaylist;

    public SongManager(Song[] playlist)
    {
        currentPlaylist = playlist;
    }

    public Song findNextSong(int currentIndex)
    {
        Song nextSong;
        int counter = currentIndex + 1;

        do
        {
            if (currentPlaylist[counter] != null)
            {
                nextSong = currentPlaylist[counter];
                break;
            }
            else
            {
                Radio.currentIndex++;
                counter++;
            }
        }
        while (true);

        return nextSong;
    }

    public Song findPreviousSong(int currentIndex)
    {
        Song previousSong;
        int counter = currentIndex - 1;

        if (counter < 0) counter = 0;

        do
        {
            if (currentPlaylist[counter] != null)
            {
                previousSong = currentPlaylist[counter];
                break;
            }
            else
            {
                Radio.currentIndex--;
                counter--;
            }
        }
        while (true);

        return previousSong;
    }
}
